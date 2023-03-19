package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.history.*;
import com.bowwowcare.sm.dto.progress.AggressionProgressRequestDto;
import com.bowwowcare.sm.dto.progress.ProgressResponseDto;
import com.bowwowcare.sm.dto.survey.AnxietyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProgressService {

    private final AnxietyHistoryRepository anxietyHistoryRepository;
    private final AggressionHistoryRepository aggressionHistoryRepository;

    public ProgressResponseDto calAnxietyProgress(List<AnxietyRequestDto> anxietyRequestDtoList, int petId) {

        //해당 pet의 가장 최신 history List 불러옴
        List<AnxietyHistory> anxietyHistoryList = anxietyHistoryRepository.findAnxietyHistoriesByPetIdOrderByCreatedDateDesc((long)petId);

        if(anxietyHistoryList.size() != 0) {

            //가장 최신의 history
            AnxietyHistory pastAnxietyHistory = anxietyHistoryList.get(0);

            int pastSituations = findAnxietyTotalSituation(pastAnxietyHistory);
            int currentSituations = findAnxietyTotalSituationByRequestDto(anxietyRequestDtoList);
            Long period = ChronoUnit.DAYS.between(pastAnxietyHistory.getCreatedDate().plusDays(1), LocalDate.now());
            List<String> msg = getAnxietyProgressMessage(pastSituations, currentSituations, period.intValue());

            return ProgressResponseDto.builder()
                    .message(msg)
                    .build();

        }
        else {
            List<String> msg = new ArrayList<>();
            msg.add("앞으로 꾸준히 문진을 해보세요!");
            return ProgressResponseDto.builder()
                    .message(msg)
                    .build();
        }
    }


    private int findAnxietyTotalSituation(AnxietyHistory anxietyHistory) {

        int result = 0;
        if(anxietyHistory.isSituation1()) { result += 1; }
        if(anxietyHistory.isSituation2()) { result += 1; }
        if(anxietyHistory.isSituation3()) { result += 1; }
        if(anxietyHistory.isSituation4()) { result += 1; }
        if(anxietyHistory.isSituation5()) { result += 1; }
        if(anxietyHistory.isSituation6()) { result += 1; }
        if(anxietyHistory.isSituation7()) { result += 1; }

        return result;
    }

    private int findAnxietyTotalSituationByRequestDto(List<AnxietyRequestDto> anxietyRequestDtoList) {

        int total = 0;
        for(AnxietyRequestDto dto : anxietyRequestDtoList) {
            if(dto.isChecked()) { total += 1; }
        }

        return total;
    }

    private List<String> getAnxietyProgressMessage(int past, int current, int period) {

        List<String> msg = new ArrayList<>();

        if(past < current) {
            msg.add("분발하세요:(");
            msg.add(period + "일 전에 비해 불안 행동이 증가했어요!");
            msg.add("앞으로 더 노력해보세요!");
        }
        if(past > current) {
            msg.add("참 잘했어요:)");
            msg.add(period + "일 전에 비해 불안 행동이 줄어들었어요!");
            msg.add("앞으로 더 노력해보세요!");
        }
        if(past == current) {
            msg.add(period + "일 전과 비교하여 불안 행동이 지속됐어요!");
            msg.add("앞으로 더 노력해보세요!");
        }
        return msg;
    }




    private List<Integer> getAggressionHistoryTypeList(AggressionHistoryType aggressionHistoryType){

        List<Integer> result = new ArrayList<>();

        if(aggressionHistoryType.isType0()) { result.add(0); }
        if(aggressionHistoryType.isType1()) { result.add(1); }
        if(aggressionHistoryType.isType2()) { result.add(2); }

        return result;
    }

    public ProgressResponseDto calAggressionProgress(AggressionProgressRequestDto requestDto, int petId) {


        List<AggressionHistory> aggressionHistoryList = aggressionHistoryRepository.findAggressionHistoriesByPetIdOrderByCreatedDateDesc((long)petId);

        if(aggressionHistoryList.size() != 0) {

            AggressionHistory history = aggressionHistoryList.get(0);

            List<Integer> pastHistoryTypeList = getAggressionHistoryTypeList(history.getAggressionHistoryType());
            List<Integer> currentHistoryTypeList = requestDto.getAggressionType();
            Long period = ChronoUnit.DAYS.between(history.getCreatedDate().plusDays(1), LocalDate.now());
            List<String> msg = getAggressionProgressMessage(pastHistoryTypeList, currentHistoryTypeList, period.intValue());

            return ProgressResponseDto.builder()
                    .message(msg)
                    .build();
        }
        else {
            List<String> msg = new ArrayList<>();
            msg.add("앞으로 꾸준히 문진을 해보세요!");
            return ProgressResponseDto.builder()
                    .message(msg)
                    .build();
        }

    }

    private List<String> getAggressionProgressMessage(List<Integer> pastType, List<Integer> currentType, int period) {

        List<String> result = new ArrayList<>();
        Collections.sort(pastType);
        Collections.sort(currentType);
        String pT = "";
        String cT = "";

        if(pastType.contains(2) && (!currentType.contains(2))) {
            pT = "극단적 행동 단계";
            cT = "행동 준비 단계";
            result.add("참 잘했어요:)");
            result.add(period + "일 만에 " + pT + "에서 " + cT + "로 바뀌었어요!");
        }
        else if((!pastType.contains(2)) && currentType.contains(2)) {
            pT = "행동 준비 단계";
            cT = "극단적 행동 단계";
            result.add("분발하세요:(");
            result.add(period + "일 만에 " + pT + "에서 " + cT + "로 바뀌었어요!");
        }
        else {
            result.add("∪･ｪ･∪");
            result.add("아이의 행동 단계가 같아요.");
        }

        result.add("앞으로 더 노력해보세요!");

        return result;
    }

}
