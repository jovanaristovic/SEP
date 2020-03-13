package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.dto.NewWorkDto;
import com.ftn.backend.model.Journal;
import com.ftn.backend.model.ScientificField;
import com.ftn.backend.model.Work;
import com.ftn.backend.repository.WorkRepository;
import com.ftn.backend.service.JournalService;
import com.ftn.backend.service.ScientificFieldService;
import com.ftn.backend.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private JournalService journalService;

    @Autowired
    private ScientificFieldService scientificFieldService;

    @Override
    public Work newWork(NewWorkDto newWorkDto)  throws IOException {

        Work work = new Work(newWorkDto);

        ScientificField scientificField =  this.scientificFieldService.findByName(newWorkDto.getScientificField());
        work.setScientificField(scientificField);

        Long id = Long.parseLong(newWorkDto.getJournalId());
        Journal journal = this.journalService.findJournalById(id);
        List<Work> workList = journal.getWorks();

        if(workList != null){
            workList.add(work);
            journal.setWorks(workList);
            this.journalService.saveJournal(journal);
        } else {
            List<Work> works = new ArrayList<>();
            works.add(work);
            journal.setWorks(works);
            this.journalService.saveJournal(journal);
        }

//        this.workRepository.save(work);



        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = new byte[0];
        try {
            decodedBytes = decoder.decodeBuffer(newWorkDto.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("src/main/pdf/" + newWorkDto.getFileName());
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(decodedBytes);
        fop.flush();
        fop.close();

        return work;
    }
}
