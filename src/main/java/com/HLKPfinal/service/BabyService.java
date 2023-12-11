package com.HLKPfinal.service;

import com.HLKPfinal.dto.BabyDto;
import com.HLKPfinal.dto.BabyResponseDto;
import com.HLKPfinal.entity.Baby;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.repository.BabyRepository;
import com.HLKPfinal.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BabyService {
    private final BabyRepository babyRepository;
    private final MemberRepository memberRepository;

    public BabyResponseDto saveBaby(BabyDto babyDto) {
        Member member = memberRepository.findById(babyDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + babyDto.getMemberId()));
        Baby baby = babyDto.toEntity(member);
        Baby savedBaby = babyRepository.save(baby);
        return BabyResponseDto.of(savedBaby);
    }
}