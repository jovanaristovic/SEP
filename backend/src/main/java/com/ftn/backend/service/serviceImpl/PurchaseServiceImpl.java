package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.model.Journal;
import com.ftn.backend.model.Purchase;
import com.ftn.backend.model.Transaction;
import com.ftn.backend.repository.JournalPurchaseRepository;
import com.ftn.backend.service.JournalService;
import com.ftn.backend.service.PurchaseService;
import com.ftn.backend.service.TransactionService;
import com.ftn.backend.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private JournalPurchaseRepository journalPurchaseRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private WorkService workService;

    @Override
    public Purchase save(Purchase purchase) {
        return journalPurchaseRepository.save(purchase);
    }

    @Override
    public void changeStatusPaid(Long id) {

        Purchase purchase = this.journalPurchaseRepository.findJournalPurchaseById(id);
        purchase.setStatus("Paid");
        this.journalPurchaseRepository.save(purchase);

        Transaction transaction = this.transactionService.findBuPurchaseId(id);
        transaction.setStatus("Paid");
        this.transactionService.save(transaction);
    }

    @Override
    public void changeStatusCanceled(Long id) {
        Purchase purchase = this.journalPurchaseRepository.findJournalPurchaseById(id);
        purchase.setStatus("Canceled");
        this.journalPurchaseRepository.save(purchase);

        Transaction transaction = this.transactionService.findBuPurchaseId(id);
        transaction.setStatus("Canceled");
        this.transactionService.save(transaction);

    }

}
