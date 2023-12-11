package com.HLKPfinal.controller;

import com.HLKPfinal.dto.BabyDto;
import com.HLKPfinal.dto.BabyResponseDto;
import com.HLKPfinal.entity.Baby;
import com.HLKPfinal.service.BabyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BabyController {
    private final BabyService babyService;

    @PostMapping("/baby")
    public ResponseEntity<BabyResponseDto> registerBaby(@RequestBody BabyDto babyDto) {
        BabyResponseDto babyResponseDto = babyService.saveBaby(babyDto);
        return new ResponseEntity<>(babyResponseDto, HttpStatus.CREATED);
    }
}