package com.HLKPfinal.service;

import com.HLKPfinal.dto.*;
import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.entity.RefreshToken;
import com.HLKPfinal.jwt.TokenProvider;
import com.HLKPfinal.repository.AuthorityRepository;
import com.HLKPfinal.repository.MemberRepository;
import com.HLKPfinal.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthorityRepository authorityRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;



    /**
     * 회원가입
     */
    @Transactional

//    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
//        // 이메일 중복 회원 검증
//        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
//            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
//        }
//
//        // 회원 정보 저장
//        return MemberResponseDto.of(memberRepository.save(memberRequestDto.toMember(passwordEncoder, authorityRepository)));
//    }

    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
        }

        // 참조 이메일이 존재하는지 검증
        String referenceEmail = memberRequestDto.getReferenceEmail();
        if (referenceEmail != null && !referenceEmail.isEmpty()) {
            if (!memberRepository.existsByEmail(referenceEmail)) {
                throw new RuntimeException("참조 이메일이 존재하지 않습니다.");
            }
        }

        Member member = memberRequestDto.toMember(passwordEncoder, authorityRepository);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    /**
     * 로그인
     */
    @Transactional
//    public TokenDto login(MemberRequestDto memberRequestDto) {
//        // Dto의 email, password를 받고 UsernamePasswordAuthenticationToken 객체 생성
//        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();
//
//        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        // JWT 토큰 생성
//        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
//
//        // refreshToken 저장
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(authentication.getName())
//                .value(tokenDto.getRefreshToken())
//                .build();
//
//        refreshTokenRepository.save(refreshToken);
//
//        return tokenDto;
//    }

    public TokenDto login(MemberRequestDto memberRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + memberRequestDto.getEmail()));

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        tokenDto.setMemberId(member.getId());
        tokenDto.setReferenceEmail(member.getReferenceEmail()); // 수정된 부분


        // refreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;

    }


    @Transactional
    public void verifyReferenceEmail(String referenceEmail) {
        if (!memberRepository.existsByEmail(referenceEmail)) {
            throw new RuntimeException("참조 이메일이 존재하지 않습니다.");
        }
    }



    /**
     * 재발급
     */
    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // access Token에서 Authentication객체 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // DB에서 member_id를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // refresh Token이 다르면
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // refreshToken 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
