package com.ftn.backend.service.serviceImpl;


import com.ftn.backend.dto.*;
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
    public JournalDto findJournalByIdAndUser(Long id, String email) {
        Journal journal = this.journalRepository.findJournalById(id);
        User user = this.userService.findUserByEmail(email);
        List<Purchase> purchases = user.getPurchases();

        String isPaid = "";
        for (Purchase purchase : purchases) {
            if (purchase.getTypeOfProduct().equals("Magazine")) {
                Journal journal1 = this.journalRepository.findJournalById(purchase.getProductId());
                if (journal1.getTitle().equals(journal.getTitle())) {
                    if (purchase.getStatus().equals("Paid")) {
                        isPaid = purchase.getStatus();
                    }
                }
            }


        }

        return new JournalDto(journal.getId(), journal.getTitle(), journal.getISSN(), journal.isOpenAccess(), journal.getScientificField(), journal.getPrice(), isPaid, journal.getWorks());
    }

    @Override
    public Journal findJournalById(Long id) {
        Journal journal = this.journalRepository.findJournalById(id);
        return journal;
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
        Set<JournalDto> journalDtos = new HashSet<JournalDto>();
        List<Journal> journals = journalRepository.findAll();
        List<Journal> journalsTemp = journalRepository.findAll();

        for (Purchase purchase : user.getPurchases()) {
            for (Journal journal : journals) {
                Journal journalInPurchase = null;

                if (purchase.getTypeOfProduct().equals("Magazine")) {
                    journalInPurchase = this.journalRepository.findJournalById(purchase.getProductId());

                    if (journalInPurchase.getTitle().equals(journal.getTitle())) {

                        JournalDto journalDto = new JournalDto(journal.getId(), journal.getTitle(), journal.getISSN(), journal.isOpenAccess(), journal.getScientificField(), journal.getPrice(), purchase.getStatus());
                        journalDtos.add(journalDto);

                        journalsTemp.remove(journal);

                    }
                }
            }

        }

        for (Journal journal : journalsTemp) {
            JournalDto journalDto = new JournalDto(journal.getId(), journal.getTitle(), journal.getISSN(), journal.isOpenAccess(), journal.getScientificField(), journal.getPrice(), "New");
            journalDtos.add(journalDto);
        }
        List<JournalDto> listDto = new ArrayList<>();

        for (JournalDto dto : journalDtos) {
            listDto.add(dto);
        }
        return listDto;
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
        BuyJournalDto buyJournalDto = null;

        if (typeOfProduct.equals("journal")) {
            journal = this.journalRepository.findJournalById(id);
            Purchase oldPurchase = this.purchaseService.findByProductIdAndTypeOfProduct(journal.getId(), "Magazine");
            if (oldPurchase != null) {
                if (oldPurchase.getStatus().equals("Canceled")) {
                    oldPurchase.setStatus("New");
                    this.purchaseService.save(oldPurchase);
                    buyJournalDto = new BuyJournalDto(id, journal.getTitle(), email, journal.getPrice(), oldPurchase.getId());

                }
            } else {
                purchase = new Purchase(journal.getId(), "New", "Magazine");
                this.purchaseService.save(purchase);
                buyJournalDto = new BuyJournalDto(id, journal.getTitle(), email, journal.getPrice(), purchase.getId());

            }
        } else {
            work = this.workService.findById(id);
            Purchase oldPurchase = this.purchaseService.findByProductIdAndTypeOfProduct(work.getId(), "Work");
            if (oldPurchase != null) {
                if (oldPurchase.getStatus().equals("Canceled")) {
                    oldPurchase.setStatus("New");
                    this.purchaseService.save(oldPurchase);
                    buyJournalDto = new BuyJournalDto(id, work.getTitle(), email, work.getPrice(), oldPurchase.getId());

                }
            } else {
                purchase = new Purchase(work.getId(), "New", "Work");
                this.purchaseService.save(purchase);
                buyJournalDto = new BuyJournalDto(id, work.getTitle(), email, work.getPrice(), purchase.getId());

            }
        }

        if (user.getPurchases() == null) {
            List<Purchase> purchases = new ArrayList<>();
            purchases.add(purchase);
            user.setPurchases(purchases);
        } else {
            user.getPurchases().add(purchase);
        }
        userService.saveUser(user);


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
