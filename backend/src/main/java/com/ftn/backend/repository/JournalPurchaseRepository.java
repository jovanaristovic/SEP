package com.ftn.backend.repository;

import com.ftn.backend.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalPurchaseRepository extends JpaRepository<Purchase, Long> {

    Purchase findJournalPurchaseById(Long id);
}
