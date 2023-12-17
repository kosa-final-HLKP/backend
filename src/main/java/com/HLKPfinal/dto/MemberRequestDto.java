package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.repository.AuthorityRepository;
import com.HLKPfinal.repository.MemberRepository;
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
    private String referenceEmail = ""; // 수정된 필드
//    private Member referenceMember; // 추가된 필드

//    public Member toMember(PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
//        Authority auth = authorityRepository.findByAuthorityStatus(this.authority)
//                .orElseThrow(() -> new RuntimeException("권한 정보가 없습니다."));
//
//
//        return Member.builder()
//                .email(email)
//                .password(passwordEncoder.encode(password))
//                .name(name)
//                .authority(auth)
//                .referenceMember(referenceMember) // 필드 추가
//                .build();
//    }

    public Member toMember(PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        Authority auth = authorityRepository.findByAuthorityStatus(this.authority)
                .orElseThrow(() -> new RuntimeException("권한 정보가 없습니다."));

        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .authority(auth)
                .referenceEmail(referenceEmail.isEmpty() ? null : referenceEmail)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}

