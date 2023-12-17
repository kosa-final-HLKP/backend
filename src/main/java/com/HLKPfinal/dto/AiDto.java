package com.HLKPfinal.dto;

public class AiDto {

    private Long memberId;
    private String token;


    // 기본 생성자
    public AiDto() {
    }

    // 생성자 with memberId and token
    public AiDto(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    // memberId getter
    public Long getMemberId() {
        return memberId;
    }

    // memberId setter
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    // token getter
    public String getToken() {
        return token;
    }

    // token setter
    public void setToken(String token) {
        this.token = token;
    }


}