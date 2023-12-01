package com.HLKPfinal.controller;

import com.HLKPfinal.service.MemberService;
import lombok.RequiredArgsConstructor;
import com.HLKPfinal.dto.MemberRequestDto;
import com.HLKPfinal.dto.MemberResponseDto;
import com.HLKPfinal.dto.TokenDto;
import com.HLKPfinal.dto.TokenRequestDto;
import com.HLKPfinal.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.save(memberRequestDto);
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