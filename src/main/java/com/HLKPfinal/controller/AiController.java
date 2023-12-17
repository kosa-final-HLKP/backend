package com.HLKPfinal.controller;

import com.HLKPfinal.dto.AiDto;
import com.HLKPfinal.entity.Ai;
import com.HLKPfinal.repository.MemberRepository;
import com.HLKPfinal.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class AiController {

    @Autowired
    private AiService aiService;

    @Autowired
    private MemberRepository memberRepository;  // MemberRepository 주입

    //이미지 다운로드
    @PostMapping("/images/{filename}")
    public void processImage() {
        aiService.processImage();
    }


//    @PostMapping("/sendToken")
//    public ResponseEntity<String> sendToken(@RequestParam("member_id") Long memberId,
//                                            @RequestHeader("Authorization") String token) {
//        aiService.sendTokenToFlask(memberId, token);
//        return ResponseEntity.ok("Token sent successfully");
//    }

    @PostMapping("/sendToken")
    public ResponseEntity<String> sendToken(@RequestBody AiDto aiDto) {
        aiService.sendTokenToFlask(aiDto.getMemberId(), aiDto.getToken());
        return ResponseEntity.ok("Token sent successfully");
    }



//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile image,
//                                         @RequestParam("memberId") Long memberId) {
//        try {
//            String imagePath = aiService.saveImage(image, memberId);
//            return ResponseEntity.ok("Image saved at: " + imagePath);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error saving image: " + e.getMessage());
//        }
//    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile image,
                                         @RequestParam("member_id") Long memberId,
                                         @RequestHeader("Authorization") String tokenHeader) {
        // 토큰 검증 로직 (여기서는 예시로 간단히 구현함)
        if (!aiService.verifyMemberId(memberId, tokenHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token");
        }
        try {
            String imagePath = aiService.saveImage(image, memberId);
            return ResponseEntity.ok("Image saved at: " + imagePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving image: " + e.getMessage());
        }
    }


//    @GetMapping("/uploads")
//    public ResponseEntity<List<byte[]>> getImages() {
//        List<byte[]> imageData = aiService.getImageData();
//        return ResponseEntity.ok(imageData);
//    }

    @GetMapping("/uploads")
    public ResponseEntity<List<String>> getImages() {
        List<String> imageData = aiService.getImageData();
        return ResponseEntity.ok(imageData);
    }


    public boolean verifyMemberId(Long memberId) {
        // DB에서 member_id에 해당하는 사용자가 존재하는지 확인
        return memberRepository.existsById(memberId);
    }

}