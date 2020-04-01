package com.ftn.backend.repository;

import com.ftn.backend.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Work findWorkByTitle(String title);
    Work findWorkById(Long id);

}
