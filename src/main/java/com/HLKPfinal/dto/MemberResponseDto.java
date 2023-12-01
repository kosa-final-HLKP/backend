package com.HLKPfinal.dto;

//import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String email;
    private String name;
//    private Set<Authority> authorities;   member.getAuthorities()
    private String auth;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail(), member.getName(), member.getAuth());
    }
}
