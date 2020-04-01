package com.ftn.backend.service;

import com.ftn.backend.dto.BuyJournalDto;
import com.ftn.backend.dto.NewJournalDto;
import com.ftn.backend.model.Journal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JournalService {

    Journal findJournalByTitle(String title);
    Journal findJournalById(Long id);
    Journal saveJournal(Journal journal);
    Journal findJournalByISSN(String ISSN);
    List<Journal> findAllJournals();
    Journal newJournal (NewJournalDto newJournalDto);
    BuyJournalDto buyJournal (Long journalId, String email);
}
