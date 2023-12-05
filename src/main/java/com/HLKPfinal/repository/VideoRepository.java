package com.HLKPfinal.repository;

import com.HLKPfinal.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<File, Long> {
    Optional<File> findByFileName(String fileName);
}
