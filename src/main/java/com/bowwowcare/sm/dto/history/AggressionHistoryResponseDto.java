package com.bowwowcare.sm.dto.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggressionHistoryResponseDto {

    private Long id;
    private List<Integer> aggressionType;
    private List<Integer> situation;
    private Long petId;
}
