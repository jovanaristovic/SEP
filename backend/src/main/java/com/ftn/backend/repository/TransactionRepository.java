package com.ftn.backend.repository;

import com.ftn.backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

        Transaction findTransactionByPurchaseId(Long id);

}
