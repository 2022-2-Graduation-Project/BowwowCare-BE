package com.bowwowcare.sm.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnxietyRequestDto {

    private boolean anxiety1;
    private boolean anxiety2;
    private boolean anxiety3;
    private boolean anxiety4;
    private boolean anxiety5;
    private boolean anxiety6;
    private boolean anxiety7;
}
