package com.HLKPfinal.service;

import com.HLKPfinal.dto.VideoPlayRequestDto;
//import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.entity.File;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.entity.Role;
import com.HLKPfinal.repository.MemberRepository;
import com.HLKPfinal.repository.VideoRepository;
import com.HLKPfinal.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VideoService {

    @Value("${video.upload.location}")
    private String dir;

    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;


    /**
     * 비디오 업로드
     */
    @Transactional
    public void uploadVideo(MultipartFile multipartFile) throws IOException {

        // 토큰에서 member 가져오기
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        // multipartFile 파일명 가져오기
        String videoName = multipartFile.getOriginalFilename();

        // resources/videos 경로에 파일명 추가
        Path location = Paths.get(dir).resolve(videoName);

        // 비디오 복사
        Files.copy(multipartFile.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);

        // DB 저장
        File video = File.builder()
                .member(member)
                .fileName(videoName)
                .filePath(location.toString())
                .build();

        videoRepository.save(video);
    }

    /**
     * 비디오 재생
     */
    @Transactional(readOnly = true)
//    public ResponseEntity<ResourceRegion> playVideo(HttpHeaders httpHeaders, VideoPlayRequestDto dto) throws IOException {
//
//        // 토큰에서 currentMemberId 가져오기
//        Long currentMemberId = SecurityUtil.getLoginMemberId();
//
//        // 파일명으로 uploader 찾기
//        Long uploader = videoRepository.findByFileName(dto.getFile()).get().getMember().getId();
//
//        // currentMemberId로 권한 가져오기
//        Set<Authority> authorities = memberRepository.findById(currentMemberId).get().getAuthorities();
//
//        // admin 체크
//        boolean isAdmin = authorities.stream()
//                .map(Authority::getAuthorityStatus)
//                .anyMatch(a -> a.equals("ROLE_ADMIN"));
//
//        // admin 권한이 없거나 업로더가 아니라면
//        if (!isAdmin && currentMemberId != uploader) {
//            throw new RuntimeException("업로더가 아니거나 admin권한이 없습니다.");
//        }
//
//        // 비디오 재생
//        FileUrlResource resource = new FileUrlResource(dir + "//" +dto.getFile());
//        ResourceRegion resourceRegion = resourceRegion(resource, httpHeaders);
//
//        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
//                .body(resourceRegion);
//    }


    public ResponseEntity<ResourceRegion> playVideo(HttpHeaders httpHeaders, VideoPlayRequestDto dto) throws IOException {
        // 토큰에서 currentMemberId 가져오기
        Long currentMemberId = SecurityUtil.getLoginMemberId();

        // 파일명으로 uploader 찾기
        Long uploader = videoRepository.findByFileName(dto.getFile())
                .map(file -> file.getMember().getId())
                .orElseThrow(() -> new RuntimeException("해당 비디오를 찾을 수 없습니다."));

        // currentMemberId로 권한 가져오기
        Set<Role> roles = Collections.singleton(memberRepository.findById(currentMemberId)
                .map(member -> member.getRole())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다.")));

        // admin 체크
        boolean isAdmin = roles.equals(Role.ROLE_ADMIN);

        // admin 권한이 없거나 업로더가 아니라면
        if (!isAdmin && !currentMemberId.equals(uploader)) {
            throw new RuntimeException("업로더가 아니거나 admin 권한이 없습니다.");
        }

        // 비디오 재생
        FileUrlResource resource = new FileUrlResource(dir + "//" + dto.getFile());
        ResourceRegion resourceRegion = resourceRegion(resource, httpHeaders);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }

    /**
     * 비디오 재생 테스트
     */
    public ResponseEntity<ResourceRegion> playVideoTest(HttpHeaders httpHeaders, @PathVariable String name) throws IOException {

        // 비디오 재생
        FileUrlResource resource = new FileUrlResource(dir + "//" + name);
        ResourceRegion resourceRegion = resourceRegion(resource, httpHeaders);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }

    // 스트림 방식 206 Partial Download
    private ResourceRegion resourceRegion(Resource video, HttpHeaders httpHeaders) throws IOException {
        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();

        if (httpHeaders.getRange().isEmpty()) {
            return new ResourceRegion(video, 0, Long.min(chunkSize, contentLength));
        }

        HttpRange httpRange = httpHeaders.getRange().stream().findFirst().get();
        long start = httpRange.getRangeStart(contentLength);
        long end = httpRange.getRangeEnd(contentLength);
        long rangeLength = Long.min(chunkSize, end - start + 1);
        return new ResourceRegion(video, start, rangeLength);
    }

    /**
     * 비디오 전체 조회
     */
    @Transactional(readOnly = true)
    public List<File> findVideos() {
        return videoRepository.findAll();
    }

}