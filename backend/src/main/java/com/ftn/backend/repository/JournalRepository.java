package com.ftn.backend.repository;

import com.ftn.backend.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    Journal findJournalByTitle(String title);
    Journal findJournalByISSN(String ISSN);
    List<Journal> findAll();
}
