package com.ftn.backend.service.serviceImpl;


import com.ftn.backend.dto.NewJournalDto;
import com.ftn.backend.model.Journal;
import com.ftn.backend.model.ScientificField;
import com.ftn.backend.repository.JournalRepository;
import com.ftn.backend.service.JournalService;
import com.ftn.backend.service.ScientificFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private ScientificFieldService scientificFieldService;

    @Override
    public Journal findJournalByTitle(String title) {
        Journal journal = this.journalRepository.findJournalByTitle(title);
        return journal;
    }

    @Override
    public Journal findJournalById(Long id) {
        return this.journalRepository.findJournalById(id);
    }

    @Override
    public Journal saveJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    @Override
    public Journal findJournalByISSN(String ISSN) {
        return this.journalRepository.findJournalByISSN(ISSN);
    }

    @Override
    public List<Journal> findAllJournals() {
        return journalRepository.findAll();
    }

    @Override
    public Journal newJournal(NewJournalDto newJournalDto) {

        Journal journal = new Journal(newJournalDto);
        ScientificField scientificField = this.scientificFieldService.findByName(newJournalDto.getScientificField());
        journal.setScientificField(scientificField);
        this.journalRepository.save(journal);

        return journal;
    }
}
