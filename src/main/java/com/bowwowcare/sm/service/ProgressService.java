package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.history.AggressionHistory;
import com.bowwowcare.sm.domain.history.AggressionHistoryRepository;
import com.bowwowcare.sm.domain.history.AnxietyHistory;
import com.bowwowcare.sm.domain.history.AnxietyHistoryRepository;
import com.bowwowcare.sm.dto.progress.ProgressResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProgressService {

    private final AnxietyHistoryRepository anxietyHistoryRepository;
    private final AggressionHistoryRepository aggressionHistoryRepository;

    public ProgressResponseDto calAnxietyProgress(int id) {

        int historyId = id;
        Long petId = anxietyHistoryRepository.getOne((long)id).getPet().getId();

        if(historyId != 1) {

            AnxietyHistory currentAnxietyHistory = anxietyHistoryRepository.getOne((long) historyId);
            List<AnxietyHistory> anxietyHistoryList = anxietyHistoryRepository.findAnxietyHistoryListByPet(petId);
            int x = anxietyHistoryList.indexOf(currentAnxietyHistory);
            AnxietyHistory pastAnxietyHistory = anxietyHistoryList.get(x-1);

            int currentSituations = findAnxietyTotalSituation(currentAnxietyHistory);
            int pastSituations = findAnxietyTotalSituation(pastAnxietyHistory);
            Long period = ChronoUnit.DAYS.between(currentAnxietyHistory.getCreatedDate(), pastAnxietyHistory.getCreatedDate());
            List<String> msg = getAnxietyProgressMessage(pastSituations, currentSituations, period.intValue() * -1);


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



    public ProgressResponseDto calAggressionProgress(int id) {

        int historyId = id;
        Long petId = aggressionHistoryRepository.getOne((long)id).getPet().getId();

        if(historyId != 1) {

            AggressionHistory currentAggressionHistory = aggressionHistoryRepository.getOne((long) historyId);
            List<AggressionHistory> aggressionHistoryList = aggressionHistoryRepository.findAggressionHistoryListByPet(petId);
            int x = aggressionHistoryList.indexOf(currentAggressionHistory);
            AggressionHistory pastAggressionHistory = aggressionHistoryList.get(x-1);


            int pastAggressionType = pastAggressionHistory.getAggressionType();
            int currentAggressionType = currentAggressionHistory.getAggressionType();

            int currentSituations = findAggressionTotalSituation(currentAggressionHistory);
            int pastSituations = findAggressionTotalSituation(pastAggressionHistory);

            Long period = ChronoUnit.DAYS.between(currentAggressionHistory.getCreatedDate(), pastAggressionHistory.getCreatedDate());
            List<String> message = getAggressionProgressMessage(pastAggressionType, currentAggressionType, pastSituations, currentSituations, period.intValue() * -1);


            return ProgressResponseDto.builder()
                    .message(message)
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

    private String getAggressionType(int type) {

        String answer = "";
        if(type == 0 || type == 1) { answer = "행동 준비 단계"; }
        if(type == 2) { answer = "극단적 행동 단계"; }

        return answer;
    }

    private List<String> getAggressionProgressMessage(int pastType, int currentType, int past, int current, int period) {

        String pT = getAggressionType(pastType);
        String cT = getAggressionType(currentType);
        List<String> result = new ArrayList<>();

        if(Objects.equals(pT, cT)){
            result.add("아이의 행동 단계가 같아요.");
            if(past < current) {
                result.add("아이의 공격 의심 상황이 "+ period + "일 전에 비해 증가했어요!");
            }
            if(past > current) {
                result.add("아이의 공격 의심 상황이 "+ period + "일 전에 비해 줄어들었어요!");
            }
            if(past == current) {
                result.add("아이의 공격 의심 상황이 " + period + "일 전과 같아요!");
            }
        }
        else {
            if(pT.equals("행동 준비 단계") && cT.equals("극단적 행동 단계")){
                result.add("분발하세요:(");
                result.add(pT + "에서 " + cT + "로 바뀌었어요!");
            }
            else if(pT.equals("극단적 행동 단계") && cT.equals("행동 준비 단계")) {
                result.add("참 잘했어요:)");
                result.add(pT + "에서 " + cT + "로 바뀌었어요!");
            }
        }
        result.add("앞으로 더 노력해보세요!");

        return result;
    }

    private int findAggressionTotalSituation(AggressionHistory aggressionHistory) {

        int result = 0;
        if(aggressionHistory.isSituation1()) { result += 1; }
        if(aggressionHistory.isSituation2()) { result += 1; }
        if(aggressionHistory.isSituation3()) { result += 1; }
        if(aggressionHistory.isSituation4()) { result += 1; }
        if(aggressionHistory.isSituation5()) { result += 1; }
        if(aggressionHistory.isSituation6()) { result += 1; }
        if(aggressionHistory.isSituation7()) { result += 1; }

        return result;
    }
}
