package com.HLKPfinal.service;

import com.HLKPfinal.dto.DiaryDto;
import com.HLKPfinal.entity.Diary;
import com.HLKPfinal.entity.File;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.jwt.TokenProvider;
import com.HLKPfinal.repository.DiaryRepository;
import com.HLKPfinal.repository.MemberRepository;
import com.HLKPfinal.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final VideoService videoService;
    private final VideoRepository videoRepository;

    @Transactional
    public Diary createDiary(Long memberId, DiaryDto diaryDto) throws IOException {
        // 사용자 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + memberId));

        // 일기 객체 생성 (아직 저장하지 않음)
        Diary diary = diaryDto.toEntity(member);

        // 동영상 업로드 및 File 객체 저장
        File file = diary.getFile();
        if (file != null) {
            String filePath = videoService.uploadVideo(diaryDto.getFile());
            file.setFilePath(filePath);
            videoRepository.save(file);
        }

        // 일기 저장
        return diaryRepository.save(diary);
    }
}