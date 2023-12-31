package com.HLKPfinal.controller;

import com.HLKPfinal.entity.Attendance;
import com.HLKPfinal.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class AttendanceController {

//    private final AttendanceService attendanceService;
//
//    @Autowired
//    public AttendanceController(AttendanceService attendanceService) {
//        this.attendanceService = attendanceService;
//    }
//
//    @PostMapping("/record")
//    public ResponseEntity<Attendance> recordAttendance(@RequestBody Map<String, Long> requestBody) {
//        Long memberId = requestBody.get("memberId");
//        if (memberId == null) {
//            // memberId가 없을 경우에 대한 처리 (예: 예외 발생)
//            throw new IllegalArgumentException("memberId must be provided in the request body");
//        }
//
//        Attendance updatedAttend = attendanceService.recordAttendance(memberId);
//        return ResponseEntity.ok(updatedAttend);
//    }
//
//    @PostMapping("/record/leave")
//    public ResponseEntity<Attendance> recordLeave(@RequestBody Map<String, Long> requestBody) {
//        Long memberId = requestBody.get("memberId");
//        if (memberId == null) {
//            // memberId가 없을 경우에 대한 처리 (예: 예외 발생)
//            throw new IllegalArgumentException("memberId must be provided in the request body");
//        }
//
//        Attendance updatedAttend = attendanceService.recordLeave(memberId);
//        return ResponseEntity.ok(updatedAttend);
//    }

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/record")
    public ResponseEntity<Attendance> recordAttendance() {
        Attendance updatedAttend = attendanceService.recordAttendance();
        return ResponseEntity.ok(updatedAttend);
    }

    @PostMapping("/record/leave")
    public ResponseEntity<Attendance> recordLeave() {
        Attendance updatedAttend = attendanceService.recordLeave();
        return ResponseEntity.ok(updatedAttend);
    }

    @GetMapping("/record/list")  // 수정된 부분
    public ResponseEntity<List<Attendance>> getAllAttendanceRecords() {
        List<Attendance> records = attendanceService.getAllAttendanceRecords();
        return ResponseEntity.ok(records);
    }




}