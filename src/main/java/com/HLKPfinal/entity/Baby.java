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
    @Column(name="baby_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private String birth;

    @Lob
    @Column(name = "etc")
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