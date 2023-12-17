package com.HLKPfinal.dto;

//import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {


    private Long memberId;

    private String email;
    private String name;
    private Authority authority;

    private String referenceEmail; // 연결된 이메일 주소
//    private Member referenceMember; // 연결된 이메일 주소

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getAuthority(),
                member.getReferenceEmail() // 수정된 부분
        );
    }


}