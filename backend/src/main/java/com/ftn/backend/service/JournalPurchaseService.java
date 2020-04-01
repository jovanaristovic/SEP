package com.ftn.backend.service;

import com.ftn.backend.dto.BuyJournalDto;
import com.ftn.backend.model.JournalPurchase;
import org.springframework.stereotype.Service;

@Service
public interface JournalPurchaseService {

    JournalPurchase save (JournalPurchase journalPurchase);
    void changeStatusPaid(Long id);
    void changeStatusCanceled(Long id);
}
