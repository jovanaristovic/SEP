package com.ftn.backend.repository;

import com.ftn.backend.model.WorkUDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkUddRepository extends JpaRepository<WorkUDD, Long> {
}
