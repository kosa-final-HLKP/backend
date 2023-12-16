package com.HLKPfinal.repository;

import com.HLKPfinal.entity.Ai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AiRepository extends JpaRepository<Ai, Long> {
    List<Ai> findAllByOrderByCreatedAtDesc();
}