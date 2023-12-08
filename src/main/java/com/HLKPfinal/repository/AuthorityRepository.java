package com.HLKPfinal.repository;

import com.HLKPfinal.entity.Authority;
//import com.HLKPfinal.entity.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Optional<Authority> findByAuthorityStatus(String authorityStatus);
}