package com.ftn.paypal.repository;

import com.ftn.paypal.model.JournalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalPlanRepository extends JpaRepository<JournalPlan, String> {

    JournalPlan findJournalPlanById(Long id);
}
