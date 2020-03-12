package com.ftn.backend.controller;

import com.ftn.backend.dto.NewJournalDto;
import com.ftn.backend.model.Journal;
import com.ftn.backend.model.ScientificField;
import com.ftn.backend.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/journal", produces = MediaType.APPLICATION_JSON_VALUE)
public class JournalController {

    @Autowired
    private JournalService journalService;


    @PostMapping(value = "/new")
    public ResponseEntity<Journal> newJournal(@RequestBody NewJournalDto newJournalDto) {

        Journal journal = this.journalService.newJournal(newJournalDto);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> journals = this.journalService.findAllJournals();
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }
}
