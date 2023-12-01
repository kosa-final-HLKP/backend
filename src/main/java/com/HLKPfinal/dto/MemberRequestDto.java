package com.HLKPfinal.dto;

import lombok.*;
//import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Member;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;
    private String name;

    private String auth;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
//                .authorities(authorities)
                .auth(auth)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
