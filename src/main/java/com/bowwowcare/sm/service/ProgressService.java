package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.history.AggressionHistoryRepository;
import com.bowwowcare.sm.domain.history.AnxietyHistory;
import com.bowwowcare.sm.domain.history.AnxietyHistoryRepository;
import com.bowwowcare.sm.dto.progress.ProgressResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
}
