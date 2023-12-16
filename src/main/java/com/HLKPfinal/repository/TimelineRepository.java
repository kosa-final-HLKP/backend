package com.HLKPfinal.repository;

import com.HLKPfinal.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    List<Timeline> findByMemberId(Long memberId);
    List<Timeline> findByMemberIdAndDate(Long memberId, Date date);
    List<Timeline> findByDate(Date date);
}

