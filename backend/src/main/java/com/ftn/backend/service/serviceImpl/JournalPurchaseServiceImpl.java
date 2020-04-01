package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.model.JournalPurchase;
import com.ftn.backend.repository.JournalPurchaseRepository;
import com.ftn.backend.service.JournalPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JournalPurchaseServiceImpl implements JournalPurchaseService {

    @Autowired
    private JournalPurchaseRepository journalPurchaseRepository;

    @Override
    public JournalPurchase save(JournalPurchase journalPurchase) {
        return journalPurchaseRepository.save(journalPurchase);
    }

    @Override
    public void changeStatusPaid(Long id) {

        JournalPurchase journalPurchase = this.journalPurchaseRepository.findJournalPurchaseById(id);
        journalPurchase.setStatus("Paid");
        this.journalPurchaseRepository.save(journalPurchase);
    }

    @Override
    public void changeStatusCanceled(Long id) {
        JournalPurchase journalPurchase = this.journalPurchaseRepository.findJournalPurchaseById(id);
        journalPurchase.setStatus("Canceled");
        this.journalPurchaseRepository.save(journalPurchase);
    }
}
