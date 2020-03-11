package com.ftn.backend.repository;

import com.ftn.backend.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findAuthorityByName(String name);
}
