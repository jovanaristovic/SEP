package com.ftn.backend.service;

import com.ftn.backend.model.Journal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JournalService {

    Journal findJournalByTitle(String title);
    Journal saveJournal(Journal journal);
    Journal findJournalByISSN(String ISSN);
    List<Journal> findAllJournals();
}
