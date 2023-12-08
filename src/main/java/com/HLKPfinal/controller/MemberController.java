package com.HLKPfinal.controller;

import com.HLKPfinal.dto.MemberResponseDto;
import com.HLKPfinal.dto.MemberUpdateDto;
import com.HLKPfinal.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
//@PreAuthorize("isAuthenticated()")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto> updateMyInfo(@RequestBody MemberUpdateDto dto) {
        memberService.updateMyInfo(dto);
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMember(HttpServletRequest request) {
        memberService.logout(request);
        memberService.deleteMember();
        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        memberService.logout(request);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}