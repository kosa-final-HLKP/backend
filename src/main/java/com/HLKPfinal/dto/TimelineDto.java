package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Member;

import java.time.LocalDateTime;
import java.util.Date;

public class TimelineDto {

    private Long memberId;
    private String role;
    private String content;
    private String category;
//    private Date date;

    private LocalDateTime dateTime;  // 수정된 부분

    public TimelineDto() {
    }

    public LocalDateTime getDateTime() {  // 수정된 부분
        return dateTime;
    }

    public TimelineDto(Long memberId, String role, String content, String category, LocalDateTime dateTime) {
        this.memberId = memberId;
        this.role = role;
        this.content = content;
        this.category = category;
//        this.date = date;
        this.dateTime = dateTime;  // 수정된 부분
    }

    // getter, setter...

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDateTime(LocalDateTime dateTime) {  // 수정된 부분
        this.dateTime = dateTime;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

}