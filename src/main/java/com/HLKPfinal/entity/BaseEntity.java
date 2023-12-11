package com.HLKPfinal.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    private LocalDateTime modifiedDate;
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public LocalDateTime getModifiedDate() {
//        return modifiedDate;
//    }
//
//    public void setModifiedDate(LocalDateTime modifiedDate) {
//        this.modifiedDate = modifiedDate;
//    }

    @CreatedDate
    @Column(name = "attendance")
    private LocalDateTime attendance;

    @LastModifiedDate
    @Column(name = "leave_time")
    private LocalDateTime leaveTime;

    public LocalDateTime getAttendance() {
        return attendance;
    }

    public void setAttendance(LocalDateTime attendance) {
        this.attendance = attendance;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }


}
