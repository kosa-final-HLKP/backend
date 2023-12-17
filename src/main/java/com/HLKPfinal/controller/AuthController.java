package com.HLKPfinal.controller;

import com.HLKPfinal.dto.*;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

//    @PostMapping("/register")
//    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
//        log.info("Login request: email={}", memberRequestDto.getEmail());
//        return ResponseEntity.ok(authService.signup(memberRequestDto));
//    }

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        log.info("Register request: email={}", memberRequestDto.getEmail());
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestBody VerifyEmailRequestDto verifyEmailRequestDto) {
        authService.verifyReferenceEmail(verifyEmailRequestDto.getEmail()); // 수정된 부분
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }

    @PostMapping("/login")  // 수정된 부분
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        TokenDto tokenDto = authService.login(memberRequestDto);  // 로그인 처리
        return ResponseEntity.ok(tokenDto);  // 응답 반환
    }


    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}