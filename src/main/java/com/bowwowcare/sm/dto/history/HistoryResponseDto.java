package com.bowwowcare.sm.dto.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryResponseDto {

    private Long id;
    private String type;
    private int situation;
    private Long petId;
}
