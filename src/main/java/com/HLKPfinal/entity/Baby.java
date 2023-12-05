//package com.HLKPfinal.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@NoArgsConstructor
//@Entity
//public class Baby {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "baby_id")
//    private Long id;
//
//    private String name;
//
//    @Temporal(TemporalType.DATE)
//    private Date birth;
//
//    @Lob
//    private byte[] etc; // Blob 타입 대신 byte[]를 사용
//
//    // Member와의 양방향 관계 설정
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MEMBER_member_id")
//    @JsonIgnore
//    private Member member;
//
//    // 생성자
//    public Baby(String name, Date birth, byte[] etc) {
//        this.name = name;
//        this.birth = birth;
//        this.etc = etc;
//    }
//
//    // Member 설정 메서드
//    public void setMember(Member member) {
//        this.member = member;
//    }
//}
