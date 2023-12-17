package com.HLKPfinal.dto;
import com.HLKPfinal.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyEmailRequestDto {
    private String email;

    // getters and setters
}