package com.HLKPfinal.repository;

import com.HLKPfinal.entity.Baby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BabyRepository extends JpaRepository<Baby, Long> {
    // 필요한 쿼리 메서드를 추가
}