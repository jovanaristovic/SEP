package com.ftn.backend.repository;

import com.ftn.backend.model.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceFieldRepository extends JpaRepository<ScientificField, Long> {

    ScientificField findScientificFieldByName(String name);
}
