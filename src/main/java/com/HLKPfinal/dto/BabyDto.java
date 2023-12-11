package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Baby;
import com.HLKPfinal.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BabyDto {
    private String name;
    private String birth;
    private String etc;
    private Long memberId;

    public Baby toEntity(Member member) {
        return Baby.builder()
                .name(name)
                .birth(birth)
                .etc(etc)
                .member(member)
                .build();
    }
}