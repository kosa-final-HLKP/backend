package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Member;
import com.HLKPfinal.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

//    private String email;
//    private String password;
//    private String name;
//
//    public Member toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities) {
//        return Member.builder()
//                .email(email)
//                .password(passwordEncoder.encode(password))
//                .name(name)
//                .authorities(authorities)
//                .build();
//    }
//
//    public UsernamePasswordAuthenticationToken toAuthentication() {
//        return new UsernamePasswordAuthenticationToken(email, password);
//    }

    private String email;
    private String password;
    private String name;

    private String role;

    private Set<Role> roles;



    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(this.password))
                .name(name)
                .role(Role.valueOf(role)) // 문자열을 Role 열거형으로 변환
                .roles(roles)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }


}

