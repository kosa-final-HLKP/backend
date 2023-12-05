//package com.HLKPfinal.controller;
//
//
//import com.HLKPfinal.entity.Baby;
//import com.HLKPfinal.entity.Member;
//import com.HLKPfinal.service.BabyService;
//import com.HLKPfinal.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/babies")
//public class BabyController {
//
//    private final BabyService babyService;
//    private final MemberService memberService;
//
//    @Autowired
//    public BabyController(BabyService babyService, MemberService memberService) {
//        this.babyService = babyService;
//        this.memberService = memberService;
//    }
//
//    // Baby 정보 추가
//    @PostMapping("/add")
//    public ResponseEntity<String> addBaby(@RequestBody BabyRequestDto requestDto) {
//        // 1. 현재 로그인한 사용자 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalEmail = authentication.getName();
//        Member member = memberService.findByEmail(currentPrincipalEmail);
//
//        // 2. Baby 정보 추가
//        babyService.addBaby(requestDto.getName(), requestDto.getBirth(), requestDto.getEtc(), member);
//        return ResponseEntity.ok("Baby 정보가 추가되었습니다.");
//    }
//
//    // 모든 Baby 정보 조회
//    @GetMapping("/all")
//    public ResponseEntity<List<Baby>> getAllBabies() {
//        List<Baby> babies = babyService.getAllBabies();
//        return ResponseEntity.ok(babies);
//    }
//
//    // 특정 사용자의 Baby 정보 조회
//    @GetMapping("/by-member")
//    public ResponseEntity<List<Baby>> getBabiesByMember() {
//        // 1. 현재 로그인한 사용자 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalEmail = authentication.getName();
//        Member member = memberService.findByEmail(currentPrincipalEmail);
//
//        // 2. 해당 사용자의 Baby 정보 조회
//        List<Baby> babies = babyService.getBabiesByMember(member);
//        return ResponseEntity.ok(babies);
//    }
//}
