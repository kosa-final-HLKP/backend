package com.HLKPfinal.controller;

import com.HLKPfinal.dto.DiaryDto;
import com.HLKPfinal.entity.Diary;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/diary")
    public ResponseEntity<Diary> createDiary(@ModelAttribute DiaryDto diaryDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Diary diary = diaryService.createDiary(memberId, diaryDto);
        return ResponseEntity.ok(diary);
    }
}