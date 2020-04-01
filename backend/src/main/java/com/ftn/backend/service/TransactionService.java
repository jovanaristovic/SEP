package com.ftn.backend.service;

import com.ftn.backend.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    Transaction save(Transaction transaction);
    Transaction findBuPurchaseId(Long id);
}
