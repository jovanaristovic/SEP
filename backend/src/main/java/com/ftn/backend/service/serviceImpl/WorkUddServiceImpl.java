package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.dto.NewWorkUddDto;
import com.ftn.backend.model.WorkElasticsearch;
import com.ftn.backend.model.WorkUDD;
import com.ftn.backend.repository.WorkUddRepository;
import com.ftn.backend.repository.elasticsearch.WorkElasticsearchRepository;
import com.ftn.backend.service.WorkUddService;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@Service
public class WorkUddServiceImpl implements WorkUddService {

    @Autowired
    WorkUddRepository workUddRepository;

    @Autowired
    WorkElasticsearchRepository workElasticsearchRepository;

    @Override
    public WorkElasticsearch newWorkUdd(NewWorkUddDto newWorkUddDto) throws IOException {

        WorkUDD work = new WorkUDD(newWorkUddDto);

        WorkUDD workUDD = this.workUddRepository.save(work);

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = new byte[0];
        try {
            decodedBytes = decoder.decodeBuffer(newWorkUddDto.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("src/main/pdf/" + newWorkUddDto.getFileName());
        String filePath = file.getPath();
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(decodedBytes);
        fop.flush();
        fop.close();

        WorkElasticsearch workElasticsearch = indexing(workUDD, filePath);

        return workElasticsearch;
    }


    private WorkElasticsearch indexing(WorkUDD workUDD, String filePath) throws UnsupportedEncodingException {

        WorkElasticsearch workElasticsearch = new WorkElasticsearch(workUDD);
        String text = parsePDF(filePath);
        workElasticsearch.setText(text);

        WorkElasticsearch workElasticsearch1 = this.workElasticsearchRepository.save(workElasticsearch);
        return workElasticsearch1;

    }


    public String parsePDF(String path) throws UnsupportedEncodingException {

        File pdf = new File(path);
        String text = null;
        try {
            System.out.println("*******************************************");
            System.out.println("Parsiranje PDF-a");
            System.out.println("Path: " + path);
            System.out.println("*******************************************");
            PDFParser parser = new PDFParser(new RandomAccessFile(pdf, "r"));
            parser.parse();
            text = getText(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public String getText(PDFParser parser) {
        try {
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(parser.getPDDocument());
            return text;
        } catch (IOException e) {
            System.out.println("Greksa pri konvertovanju dokumenta u pdf");
        }
        return null;
    }

}
