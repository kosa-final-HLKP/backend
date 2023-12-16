package com.HLKPfinal.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Baby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long babyId;

    @Column
    private String name;

    @Column
    private String birth;

    @Lob
    @Column
    private String etc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Baby(String name, String birth, String etc, Member member) {
        this.name = name;
        this.birth = birth;
        this.etc = etc;
        this.member = member;
    }
}