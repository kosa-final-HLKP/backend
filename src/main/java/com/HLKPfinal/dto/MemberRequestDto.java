package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;
    private String name;

    private String authority; // 동적으로 선택한 권한

//    public Member toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities) {
//        return Member.builder()
//                .email(email)
//                .password(passwordEncoder.encode(password))
//                .name(name)
//                .authorities(authorities)
//                .build();
//    }

//    public Member toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities) {
//        Set<Authority> authoritySet = this.authorities.stream()
//                .map(authority -> Authority.builder().authorityStatus(authority).build())
//                .collect(Collectors.toSet());


    public Member toMember(PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        Authority auth = authorityRepository.findByAuthorityStatus(this.authority)
                .orElseThrow(() -> new RuntimeException("권한 정보가 없습니다."));

        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .authority(auth)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}

