package com.HLKPfinal.dto;

//import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {


//    private String email;
//    private String name;
//    private Set<Authority> authorities;
//
//    public static MemberResponseDto of(Member member) {
//        return new MemberResponseDto(member.getEmail(), member.getName(), member.getAuthorities());
//    }

    private String email;
    private String name;
    private Role role;

//    public MemberResponseDto(String email, String name, Role role) {
//        this.email = email;
//        this.name = name;
//        this.role = role;
//    }

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail(), member.getName(), member.getRole());
    }

}