package com.HLKPfinal.controller;

import com.HLKPfinal.dto.MemberRequestDto;
import com.HLKPfinal.dto.MemberResponseDto;
import com.HLKPfinal.dto.TokenDto;
import com.HLKPfinal.dto.TokenRequestDto;
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

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        log.info("Login request: email={}", memberRequestDto.getEmail());
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestBody String email) {
        return ResponseEntity.ok(authService.verifyEmail(email));
    }


//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
//        return ResponseEntity.ok(authService.login(memberRequestDto));
//    }

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