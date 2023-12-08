package com.HLKPfinal.controller;

import com.HLKPfinal.dto.MemberRequestDto;
import com.HLKPfinal.dto.MemberResponseDto;
import com.HLKPfinal.dto.TokenDto;
import com.HLKPfinal.dto.TokenRequestDto;
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


    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}