package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.model.Transaction;
import com.ftn.backend.repository.TransactionRepository;
import com.ftn.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    @Override
    public Transaction findBuPurchaseId(Long id) {
        return this.transactionRepository.findTransactionByPurchaseId(id);
    }
}
