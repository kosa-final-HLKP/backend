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

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getId(), member.getEmail(), member.getName(), member.getAuthority());
    }


}