//package com.HLKPfinal.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Authority {
//
//    @Id
//    @Column(name = "authority_status")
//    @Enumerated(EnumType.STRING)
//    private Role authorityStatus;
//
//    public String getAuthorityStatus() {
//        return this.authorityStatus.toString();
//    }
//
//    @Builder
//    public Authority(Role authorityStatus) {
//        this.authorityStatus = authorityStatus;
//    }
//}
