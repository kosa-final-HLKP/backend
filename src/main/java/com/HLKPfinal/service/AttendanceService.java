    package com.HLKPfinal.service;

    import com.HLKPfinal.entity.Attendance;
    import com.HLKPfinal.entity.Member;
    import com.HLKPfinal.repository.AttendanceRepository;
    import com.HLKPfinal.repository.MemberRepository;
    import com.HLKPfinal.util.SecurityUtil;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.List;

    @Service
    public class AttendanceService {

//        private final AttendanceRepository attendanceRepository;
//        private final MemberRepository memberRepository;
//
//        @Autowired
//        public AttendanceService(AttendanceRepository attendanceRepository, MemberRepository memberRepository) {
//            this.attendanceRepository = attendanceRepository;
//            this.memberRepository = memberRepository;
//        }
//
//        public Attendance recordAttendance(Long memberId) {
//            Member member = memberRepository.findById(memberId)
//                    .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));
//
//            Attendance attendance = new Attendance(LocalDateTime.now(), member);
//            attendance.recordAttendance();
//            return attendanceRepository.save(attendance);
//        }
//
//        public Attendance recordLeave(Long memberId) {
//            Member member = memberRepository.findById(memberId)
//                    .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));
//
//            Attendance attendance = new Attendance(LocalDateTime.now(), member);
//            attendance.recordLeave();
//            return attendanceRepository.save(attendance);
//        }

        private final AttendanceRepository attendanceRepository;
        private final MemberRepository memberRepository;

        @Autowired
        public AttendanceService(AttendanceRepository attendanceRepository, MemberRepository memberRepository) {
            this.attendanceRepository = attendanceRepository;
            this.memberRepository = memberRepository;
        }

        public List<Attendance> getAllAttendanceRecords() {
            Long memberId = SecurityUtil.getLoginMemberId();
            return attendanceRepository.findByMemberId(memberId);
        }

        public Attendance recordAttendance() {
            Long memberId = SecurityUtil.getLoginMemberId();
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

            Attendance attendance = new Attendance(member);
            attendance.recordAttendance();
            return attendanceRepository.save(attendance);
        }

        public Attendance recordLeave() {
            Long memberId = SecurityUtil.getLoginMemberId();
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

            LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
            Attendance attendance = attendanceRepository.findByMemberIdAndAttendanceAfter(memberId, today)
                    .orElseThrow(() -> new RuntimeException("Attendance not found for today: " + memberId));

            attendance.recordLeave();
            return attendanceRepository.save(attendance);
        }


    }