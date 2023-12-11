package com.HLKPfinal.repository;

import com.HLKPfinal.entity.Attendance;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByMemberId(Long memberId);
    Optional<Attendance> findByMemberIdAndAttendanceAfter(Long memberId, LocalDateTime dateTime);

}