package com.HLKPfinal.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Ai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ai_id")
    private Long id;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData; // 이미지 데이터를 바이트 배열로 저장

    @Column(name = "created_at")
    @CreatedDate // 이 어노테이션을 추가하여 객체가 생성될 때 자동으로 현재 시간이 설정되도록 합니다.
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 기본 생성자
    public Ai() {
    }


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}