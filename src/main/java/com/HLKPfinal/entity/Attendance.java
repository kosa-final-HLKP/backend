package com.HLKPfinal.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance extends BaseEntity {

//    @Id
//    @GeneratedValue
//    @Column(name = "attendance_id")
//    private Long id;
//
//    private LocalDateTime attendance;
//
//    private LocalDateTime leaveTime;
//
//    // N:1 관계 설정
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    // 생성자 추가
//    public Attendance(LocalDateTime attendance, Member member) {
//        this.attendance = attendance;
//        this.member = member;
//    }
//
//    public void recordAttendance() {
//        this.attendance = LocalDateTime.now();
//        setCreatedDate(LocalDateTime.now());
//    }
//
//    public void recordLeave() {
//        this.leaveTime = LocalDateTime.now();
//        setModifiedDate(LocalDateTime.now());
//    }

    @Id
    @GeneratedValue
    @Column(name = "attendance_id")
    private Long id;

    // N:1 관계 설정
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 생성자 추가
    public Attendance(Member member) {
        this.member = member;
    }

    public void recordAttendance() {
        setAttendance(LocalDateTime.now());
    }

    public void recordLeave() {
        setLeaveTime(LocalDateTime.now());
    }
}