package com.HLKPfinal.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority implements Serializable {

//    @Id
//    @Column(name = "authority_status")
////    @Enumerated(EnumType.STRING)
//    private String authorityStatus;

    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private Long id;

    @Column(name = "authority_status", unique = true)
    private String authorityStatus;

    @Builder
    public Authority(String authorityStatus) {
        this.authorityStatus = authorityStatus;
    }

    public String getAuthorityStatus() {
        return this.authorityStatus;
    }

}
