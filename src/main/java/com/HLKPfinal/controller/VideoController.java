package com.HLKPfinal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.HLKPfinal.entity.Video;
import com.HLKPfinal.dto.VideoListResponseDto;
import com.HLKPfinal.dto.VideoListDto;
import com.HLKPfinal.dto.VideoPlayRequestDto;
import com.HLKPfinal.service.VideoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class VideoController {

    @Value("${video.upload.location}")
    private String dir;

    private final VideoService videoService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> uploadVideo(MultipartFile file) throws IOException {
        if (file == null) {
            return new ResponseEntity<>("업로드할 파일이 전송되지 않았습니다.", HttpStatus.BAD_REQUEST);
        }

        if (file.isEmpty()) {
            return new ResponseEntity<>("업로드할 파일이 비어있습니다.", HttpStatus.BAD_REQUEST);
        }

        videoService.uploadVideo(file);
        return new ResponseEntity<>("업로드 성공", HttpStatus.OK);
    }

//    public ResponseEntity<String> uploadVideo(MultipartFile file) throws IOException {
//        videoService.uploadVideo(file);
//        return new ResponseEntity<>("업로드 성공", HttpStatus.OK);
//    }

    @GetMapping("/play/{name}")
    public ResponseEntity<ResourceRegion> playVideo
            (@RequestHeader HttpHeaders httpHeaders, @RequestBody VideoPlayRequestDto dto) throws IOException {
        return videoService.playVideo(httpHeaders, dto);
    }

    // 크롬에서 테스트시 문제없음 postman에서는 처음으로 돌아감
    @GetMapping("/playTest/{name}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<ResourceRegion> playVideoTest
    (@RequestHeader HttpHeaders httpHeaders, @PathVariable String name) throws IOException {
        return videoService.playVideoTest(httpHeaders, name);
    }

    @GetMapping("/list")
    public VideoListResponseDto findVideos() {
        List<Video> videos = videoService.findVideos();
        List<VideoListDto> collect = videos.stream()
                .map(v -> new VideoListDto(v.getOrigFileName()))
                .collect(Collectors.toList());

        return new VideoListResponseDto(collect.size(), collect);
    }
}