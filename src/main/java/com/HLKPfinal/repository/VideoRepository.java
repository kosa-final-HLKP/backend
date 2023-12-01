package com.HLKPfinal.repository;

import com.HLKPfinal.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByOrigFileName(String origFileName);
}
