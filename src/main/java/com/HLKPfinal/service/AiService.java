package com.HLKPfinal.service;

import com.HLKPfinal.dto.AiDto;
import com.HLKPfinal.entity.Ai;
import com.HLKPfinal.jwt.TokenProvider;
import com.HLKPfinal.repository.AiRepository;
import com.HLKPfinal.repository.MemberRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AiService {

    @Autowired
    private AiRepository aiRepository;

    @Autowired
    private MemberService memberService; // MemberService 주입

    @Autowired
    private TokenProvider tokenProvider;

    private final WebClient webClient;

    public AiService() {
        this.webClient = WebClient.create("http://localhost:5000");
    }

    public void sendTokenToFlask(Long memberId, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = "http://127.0.0.1:5000/sendToken"; // Flask 엔드포인트 주소

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject requestBody = new JSONObject();
        requestBody.put("member_id", memberId);
        requestBody.put("token", token);

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        restTemplate.postForObject(flaskUrl, request, String.class);
    }


    // Flask에서 이미지를 처리하고 반환된 경로를 저장하는 메서드
    public String processImage() {
        return webClient.post()
                .uri("/upload")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

//    public String saveImage(MultipartFile image, Long memberId) throws IOException {
//        String filename = StringUtils.cleanPath(image.getOriginalFilename());
//        Path imagePath = Paths.get("images").resolve(filename);
//        try {
//            Files.createDirectories(imagePath.getParent());  // 경로가 존재하지 않는 경우 생성
//            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new IOException("Could not save image file: " + filename, e);
//        }
//
//        Ai ai = new Ai();
//        ai.setImagePath(imagePath.toString());
//        ai.setMember(memberService.getMemberById(memberId));
//        ai.setCreatedAt(LocalDateTime.now());
//        aiRepository.save(ai);
//
//        return imagePath.toString();
//    }

    public String saveImage(MultipartFile image, Long memberId) throws IOException {
        // 이미지 데이터를 바이트 배열로 변환
        byte[] imageData = image.getBytes();

        // Ai 엔티티 생성 및 저장
        Ai ai = new Ai();
        ai.setImageData(imageData);
        ai.setMember(memberService.getMemberById(memberId));
        ai.setCreatedAt(LocalDateTime.now());
        aiRepository.save(ai);

        return "Image saved successfully"; // 이미지가 성공적으로 저장됐음을 알림
    }

    
//    public List<byte[]> getImageData() {
//        return aiRepository.findAllByOrderByCreatedAtDesc()
//                .stream()
//                .map(Ai::getImageData)
//                .collect(Collectors.toList());
//    }

    public List<String> getImageData() {
        return aiRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(ai -> Base64.getEncoder().encodeToString(ai.getImageData()))
                .collect(Collectors.toList());
    }


    // 이미지 정보를 Ai 객체로 저장하는 메서드
    public void save(Ai ai) {
        aiRepository.save(ai);
    }



    // Member ID 검증 메서드 (실제 토큰 검증 로직 필요)
    public boolean verifyMemberId(Long memberId, String tokenHeader) {
        // 토큰에서 member_id 추출 및 검증 로직
        String token = tokenHeader.replace("Bearer ", "");
        if (tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            Long tokenMemberId = Long.parseLong(authentication.getName());
            return memberId.equals(tokenMemberId);
        }
        return false;
    }

}