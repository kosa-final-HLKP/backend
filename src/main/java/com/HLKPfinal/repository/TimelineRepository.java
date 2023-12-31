package com.HLKPfinal.repository;

import com.HLKPfinal.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    List<Timeline> findByMemberId(Long memberId);
    List<Timeline> findByMemberIdAndDateTime(Long memberId, LocalDateTime dateTime);  // 수정된 부분
    List<Timeline> findByDateTime(LocalDateTime dateTime);  // 수정된 부분

    List<Timeline> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}


