package com.bowwowcare.sm.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterResponseDto {

    // 로그인 PK
    private Long id;

    //로그인 아이디
    private String email;


    @Builder
    public UserRegisterResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
