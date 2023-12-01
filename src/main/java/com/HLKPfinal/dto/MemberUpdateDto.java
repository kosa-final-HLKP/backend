package com.HLKPfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {

    private String email;
    private String name;
    private String password;
}
