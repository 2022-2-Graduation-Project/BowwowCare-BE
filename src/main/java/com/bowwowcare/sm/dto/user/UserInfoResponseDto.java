package com.bowwowcare.sm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDto {

    private Long id;
    private String email;
    private String username;
    private String profileImage;
    private int theme;
    private int reward;
    private List<Integer> availableTheme;
}
