package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Baby;
import lombok.Getter;

@Getter
public class BabyResponseDto {
    private Long babyId;
    private String name;
    private String birth;
    private String etc;
    private Long memberId;

    public static BabyResponseDto of(Baby baby) {
        BabyResponseDto dto = new BabyResponseDto();
        dto.babyId = baby.getBabyId();
        dto.name = baby.getName();
        dto.birth = baby.getBirth();
        dto.etc = baby.getEtc();
        dto.memberId = baby.getMember().getId(); // Member 엔티티에서 ID를 가져옵니다.
        return dto;
    }
}