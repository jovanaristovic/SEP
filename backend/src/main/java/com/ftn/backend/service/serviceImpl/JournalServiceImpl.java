package com.ftn.backend.service.serviceImpl;


import com.ftn.backend.dto.BuyJournalDto;
import com.ftn.backend.dto.NewJournalDto;
import com.ftn.backend.model.*;
import com.ftn.backend.repository.JournalRepository;
import com.ftn.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private ScientificFieldService scientificFieldService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WorkService workService;

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
    public HttpEntity buyJournal(Long id, String email, String typeOfProduct) {

        User user = this.userService.findUserByEmail(email);

        Purchase purchase = null;
        Journal journal = null;
        Work work = null;
        if(typeOfProduct.equals("journal")) {
             journal = this.journalRepository.findJournalById(id);
            purchase = new Purchase(journal.getId(), "New", "journal");
            this.purchaseService.save(purchase);
        } else{
            work = this.workService.findById(id);
            purchase = new Purchase(work.getId(), "New", "work");
            this.purchaseService.save(purchase);
        }

        if(user.getPurchases() == null){
            List<Purchase> purchases = new ArrayList<>();
            purchases.add(purchase);
            user.setPurchases(purchases);
        } else {
            user.getPurchases().add(purchase);
        }
        userService.saveUser(user);

        BuyJournalDto buyJournalDto = null;
        if (typeOfProduct.equals("journal")) {

            Transaction transaction = new Transaction("journal", id, email, journal.getPrice(), "New", purchase.getId());
            this.transactionService.save(transaction);
            buyJournalDto = new BuyJournalDto(id, journal.getTitle(), email, journal.getPrice(), purchase.getId());


        } else {
            Transaction transaction = new Transaction("work", id, email, work.getPrice(), "New", purchase.getId());
            this.transactionService.save(transaction);
            buyJournalDto = new BuyJournalDto(id, work.getTitle(), email, work.getPrice(), purchase.getId());
        }


        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity<>(buyJournalDto, requestHeaders);

        return requestEntity;
    }
}
