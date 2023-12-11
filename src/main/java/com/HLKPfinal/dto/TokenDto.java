package com.HLKPfinal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private Long memberId;  // 추가

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;

}
