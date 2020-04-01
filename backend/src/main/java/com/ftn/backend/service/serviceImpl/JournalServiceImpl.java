package com.ftn.backend.service.serviceImpl;


import com.ftn.backend.dto.BuyJournalDto;
import com.ftn.backend.dto.NewJournalDto;
import com.ftn.backend.model.Journal;
import com.ftn.backend.model.JournalPurchase;
import com.ftn.backend.model.ScientificField;
import com.ftn.backend.model.User;
import com.ftn.backend.repository.JournalRepository;
import com.ftn.backend.service.JournalPurchaseService;
import com.ftn.backend.service.JournalService;
import com.ftn.backend.service.ScientificFieldService;
import com.ftn.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private ScientificFieldService scientificFieldService;

    @Autowired
    private JournalPurchaseService journalPurchaseService;

    @Autowired
    private UserService userService;

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

    @Override
    public BuyJournalDto buyJournal(Long journalId, String email) {

        Journal journal = this.journalRepository.findJournalById(journalId);

        JournalPurchase journalPurchase = new JournalPurchase(journal, "New");
        this.journalPurchaseService.save(journalPurchase);

        User user = this.userService.findUserByEmail(email);

        if(user.getJournalPurchases() == null){
            List<JournalPurchase> journalPurchases = new ArrayList<>();
            journalPurchases.add(journalPurchase);
            user.setJournalPurchases(journalPurchases);
        } else {
            user.getJournalPurchases().add(journalPurchase);
        }
        userService.saveUser(user);


        BuyJournalDto buyJournalDto = new BuyJournalDto(journalId, journal.getTitle(), email, journal.getPrice(), journalPurchase.getId());

        return buyJournalDto;
    }
}
