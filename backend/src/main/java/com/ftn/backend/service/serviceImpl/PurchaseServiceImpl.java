package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.model.Purchase;
import com.ftn.backend.repository.JournalPurchaseRepository;
import com.ftn.backend.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private JournalPurchaseRepository journalPurchaseRepository;


    @Override
    public Purchase save(Purchase purchase) {
        return journalPurchaseRepository.save(purchase);
    }

    @Override
    public void changeStatusPaid(Long id) {

        Purchase purchase = this.journalPurchaseRepository.findJournalPurchaseById(id);
        purchase.setStatus("Paid");
        this.journalPurchaseRepository.save(purchase);
    }

    @Override
    public void changeStatusCanceled(Long id) {
        Purchase purchase = this.journalPurchaseRepository.findJournalPurchaseById(id);
        purchase.setStatus("Canceled");
        this.journalPurchaseRepository.save(purchase);

    }

    @Override
    public Purchase findByProductIdAndTypeOfProduct(Long productId, String typeOfProduct) {
        return this.journalPurchaseRepository.findByProductIdAndTypeOfProduct(productId, typeOfProduct);
    }

}
