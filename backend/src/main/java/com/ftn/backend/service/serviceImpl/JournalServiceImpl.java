package com.ftn.backend.service.serviceImpl;


import com.ftn.backend.dto.BuyJournalDto;
import com.ftn.backend.dto.JournalDto;
import com.ftn.backend.dto.NewJournalDto;
import com.ftn.backend.dto.SubscribeDto;
import com.ftn.backend.model.*;
import com.ftn.backend.model.enumeration.FrequencyPayment;
import com.ftn.backend.model.enumeration.PaymentTypePlan;
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
    public JournalDto findJournalByIdAndUser(Long id, String email)
    {   Journal journal = this.journalRepository.findJournalById(id);
        User user = this.userService.findUserByEmail(email);
        List<Purchase> purchases = user.getPurchases();

        boolean isPaid = false;
        for(Purchase purchase:purchases){
            if(purchase.getTypeOfProduct().equals("Magazine")){
                Journal journal1 = this.journalRepository.findJournalById(purchase.getProductId());
                if(journal1.getTitle().equals(journal.getTitle())){
                    if(purchase.getStatus().equals("Paid")){
                        isPaid=true;
                    }
                }
            }

        }

        return new JournalDto(journal.getId(),journal.getTitle(),journal.getISSN(),journal.isOpenAccess(),journal.getScientificField(),journal.getPrice(),isPaid, journal.getWorks()) ;
    }

    @Override
    public Journal findJournalById(Long id)
    {   Journal journal = this.journalRepository.findJournalById(id);
        return journal ;
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
    public List<JournalDto> findAllJournals(String email) {
        User user = this.userService.findUserByEmail(email);
        List<JournalDto> journalDtos = new ArrayList<>();
        List<Journal> journals = journalRepository.findAll();
        List<Journal> journalsTemp = journalRepository.findAll();
        boolean isPaid = false;

        for (Purchase purchase : user.getPurchases()) {
                for (Journal journal : journals) {
                    Journal journalInPurchase = null;

                    if (purchase.getTypeOfProduct().equals("Magazine")) {
                        journalInPurchase = this.journalRepository.findJournalById(purchase.getProductId());

                        if (journalInPurchase.getTitle().equals(journal.getTitle())) {
                            if (purchase.getStatus().equals("Paid")) {
                                isPaid = true;
                            }
                            JournalDto journalDto = new JournalDto(journal.getId(), journal.getTitle(), journal.getISSN(), journal.isOpenAccess(), journal.getScientificField(), journal.getPrice(), isPaid);
                            journalDtos.add(journalDto);

                            journalsTemp.remove(journal);

                        }
                    }
                }

        }

        for (Journal journal:journalsTemp){
            JournalDto journalDto = new JournalDto(journal.getId(),journal.getTitle(), journal.getISSN(), journal.isOpenAccess(), journal.getScientificField(), journal.getPrice(), false);
            journalDtos.add(journalDto);
        }

        return journalDtos;
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
            purchase = new Purchase(journal.getId(), "New", "Magazine");
            this.purchaseService.save(purchase);
        } else{
            work = this.workService.findById(id);
            purchase = new Purchase(work.getId(), "New", "Work");
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

            Transaction transaction = new Transaction("Magazine", id, email, journal.getPrice(), "New", purchase.getId());
            this.transactionService.save(transaction);
            buyJournalDto = new BuyJournalDto(id, journal.getTitle(), email, journal.getPrice(), purchase.getId());


        } else {
            Transaction transaction = new Transaction("Work", id, email, work.getPrice(), "New", purchase.getId());
            this.transactionService.save(transaction);
            buyJournalDto = new BuyJournalDto(id, work.getTitle(), email, work.getPrice(), purchase.getId());
        }


        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity<>(buyJournalDto, requestHeaders);

        return requestEntity;
    }

    @Override
    public SubscribeDto subscribeJournal(Long journalId, String email, int period) {

        Journal journal = findJournalById(journalId);

        double price = journal.getPrice() / period / 2;

        SubscribeDto subscribeDto = new SubscribeDto();
        subscribeDto.setPrice(price);
        subscribeDto.setCurrency("EUR");
        subscribeDto.setTitleOfJournal(journal.getTitle());
        subscribeDto.setDescription(journal.getISSN());
        subscribeDto.setTypeOfPlan(PaymentTypePlan.REGULAR);
        subscribeDto.setFrequencyPayment(FrequencyPayment.MONTH);
        subscribeDto.setFrequencyInterval(1);
        subscribeDto.setCycles(0);
        return subscribeDto;
    }
}
