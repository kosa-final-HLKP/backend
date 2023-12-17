package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private Long memberId;

    private String email;  // 사용자의 이메일 주소
    private String referenceEmail;  // 연결된 이메일 주소 필드 추가


    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;

}
