package com.ftn.backend.repository;

import com.ftn.backend.model.JournalPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalPurchaseRepository extends JpaRepository<JournalPurchase, Long> {

    JournalPurchase findJournalPurchaseById(Long id);
}
