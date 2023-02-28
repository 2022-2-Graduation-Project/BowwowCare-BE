package com.bowwowcare.sm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoUpdateResponseDto {

    private int id;
    private String username;
    private String profileImage;
    private int theme;
}
