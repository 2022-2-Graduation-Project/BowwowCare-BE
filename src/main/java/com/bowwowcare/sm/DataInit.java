package com.bowwowcare.sm;

import com.bowwowcare.sm.domain.survey.AggressionBehavior;
import com.bowwowcare.sm.domain.survey_question.AggressionQuestion;
import com.bowwowcare.sm.domain.survey_question.AnxietyQuestion;
import com.bowwowcare.sm.domain.survey_result.AggressionResult;
import com.bowwowcare.sm.domain.survey_result.AnxietyResult;
import com.bowwowcare.sm.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final SurveyService surveyService;

    @PostConstruct
    public void init() {

        //공격행동 문진 결과 - situation, solution 저장
        AggressionResult aggressionResult1 = surveyService.saveAggression(
                AggressionResult.builder()
                        .situation("다른 방문자가 왔어요.".getBytes())
                        .solution("사회화 시기에 사람, 다른 동물 등에 대한 사회화의 기회가 없는 경우에 발생할 수 있습니다. 낯선 사람이 간식을 줘보도록 해보세요.".getBytes())
                        .build()
        );
        AggressionResult aggressionResult2 = surveyService.saveAggression(
                AggressionResult.builder()
                        .situation("영역에 과도하게 집착하고 있어요.".getBytes())
                        .solution("좋아하는 사람, 낯선 사람 방문순으로 단계적으로 교정해보세요.".getBytes())
                        .build()
        );
        AggressionResult aggressionResult3 = surveyService.saveAggression(
                AggressionResult.builder()
                        .situation("가족을 심하게 졸졸 따라다니고 있어요.".getBytes())
                        .solution("아이만의 공간을 만들어 그곳에서 편히 먹거나 쉬도록 켄넬교육을 시켜보세요.".getBytes())
                        .build()
        );
        AggressionResult aggressionResult4 = surveyService.saveAggression(
                AggressionResult.builder()
                        .situation("새로운 곳으로 이사하거나 새로운 가족이 생겼어요.".getBytes())
                        .solution("아이만의 공간을 만들어 그곳에서 편히 먹거나 쉬도록 켄넬교육을 시켜보세요.".getBytes())
                        .build()
        );
        AggressionResult aggressionResult5 = surveyService.saveAggression(
                AggressionResult.builder()
                        .situation("보호자가 개의 입에 있는 물건이나 가지고 있는 물건을 강제로 빼앗았어요.".getBytes())
                        .solution("교환하기를 알려주세요. 장난감의 경우 똑같은 장난감 2-3개를 준비하여 교환하기를 알려주세요.".getBytes())
                        .build()
        );
        AggressionResult aggressionResult6 = surveyService.saveAggression(
                AggressionResult.builder()
                        .situation("먹이가 충분하지 않아요.".getBytes())
                        .solution("어린 강아지에게 먹이가 부족할 경우 과도한 식탐과 소유성 공격행동이 유발 될 수 있어요. 손으로 사료를 줘보세요. 어렵다면 처음에는 바닥에 던져주고 점진적으로 손으로 줘보세요 이때 칭찬과 쓰다듬기는 필수!".getBytes())
                        .build()
        );
        AggressionResult aggressionResult7 = surveyService.saveAggression(
                AggressionResult.builder()
                        .situation("사람이 강아지를 힘으로 통제했어요.".getBytes())
                        .solution("개는 억제와 좌절감을 연관 짓는 법을 학습하게 되어 공분하지 않은 상태에서도 자신의 욕구가 좌절되거나, 어제되었을 때 공격적인 반응을 보일 수 있습니다. ‘여기 엎드려’, ‘놔둬’ 교육을 철저히 하고 서로 떨어져 있으면 무시하고 개들이 함께 있을 때는 개들과 함께 즐거운 게임이나 놀이를 하며 간식을 양손으로 줘보세요.".getBytes())
                        .build()
        );



        //분리불안 문진 situation 저장
        AnxietyResult anxietyResult1 = surveyService.saveAnxiety(
                AnxietyResult.builder()
                        .situation("외출하기 전".getBytes())
                        .solution1("보호자가 없을 때 좋은 일이 벌어진다는 것을 알려주는 방법입니다. 예를 들어, 혼자 두고 나갈 때 오래 먹을 수 있는 뼈 간식 같은 맛있는 음식을 주고 나가보세요. 혼자 있을 때 좋은 일이 일어나고 긍정적인 감정을 느끼도록요!".getBytes())
                        .solution2("개는 에너지를 발산하면 일단 쉬고 싶어하기 때문에 신체적, 정신적으로 풍부하게 해주게 되면 휴식을 취하게 되어 분리불안 해소에 도움이 돼요.외출 전 운동이나 놀이를 해주세요.".getBytes())
                        .build()
        );
        AnxietyResult anxietyResult2 = surveyService.saveAnxiety(
                AnxietyResult.builder()
                        .situation("훈련".getBytes())
                        .solution1("외출할 것처럼 준비하다가 외출하지 않고 무시한 채 일상적인 행동을 하는 것을 반복하여 연습해주세요.".getBytes())
                        .solution2("켄넬교육을 시켜 자신만의 편안한 공간을 만들어 주어 그곳에서 쉴 수 있도록 해주세요.".getBytes())
                        .solution3("분리불안의 증상으로 배변 실수, 짖음 등을 보이는 경우 신체적 처벌은 오히려 부작용과 역효과의 우려가 있습니다".getBytes())
                        .build()
        );
        AnxietyResult anxietyResult3 = surveyService.saveAnxiety(
                AnxietyResult.builder()
                        .situation("외출 후".getBytes())
                        .solution1("외출 후 돌아왔을 때 절대 안아주거나, 만져주거나, 말을 걸거나, 눈을 마주쳐 아는 척하지 말고 무시해주세요. 아이가 차분해졌을 때 ‘앉아, 기다려’ 후에 보상을 해주세요.".getBytes())
                        .build()
        );
        AnxietyResult anxietyResult4 = surveyService.saveAnxiety(
                AnxietyResult.builder()
                        .situation("분리불안에 해당되지 않아요!".getBytes())
                        .solution1("걱정하지 않으셔도 됩니다!".getBytes())
                        .build()
        );


        AggressionBehavior aggressionBehavior1 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(0)
                        .content("헐떡이며 숨쉬기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior2 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(0)
                        .content("동공확장".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior3 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(0)
                        .content("몸 떨기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior4 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(0)
                        .content("털 세우기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior5 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(1)
                        .content("입 다물기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior6 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(1)
                        .content("경직되기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior7 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(1)
                        .content("짖기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior8 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(1)
                        .content("응시하기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior9 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(2)
                        .content("이빨 보이기".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior10 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(2)
                        .content("도망/철수".getBytes())
                        .build()
        );
        AggressionBehavior aggressionBehavior11 = surveyService.saveAggressionBehavior(
                AggressionBehavior.builder()
                        .aggressionType(2)
                        .content("달려들기 & 한 번 물기".getBytes())
                        .build()
        );


        AggressionQuestion aggressionQuestion1 = surveyService.saveAggressionQuestion(
                AggressionQuestion.builder()
                        .content("낯선자가 방문했나요?".getBytes())
                        .build()
        );
        AggressionQuestion aggressionQuestion2 = surveyService.saveAggressionQuestion(
                AggressionQuestion.builder()
                        .content("영역에 과도하게 집착하고 있나요?".getBytes())
                        .build()
        );
        AggressionQuestion aggressionQuestion3 = surveyService.saveAggressionQuestion(
                AggressionQuestion.builder()
                        .content("가족을 심하게 졸졸 따라다니나요?".getBytes())
                        .build()
        );
        AggressionQuestion aggressionQuestion4 = surveyService.saveAggressionQuestion(
                AggressionQuestion.builder()
                        .content("이사를 가거나 새로운 가족 구성원이 생겼나요?".getBytes())
                        .build()
        );
        AggressionQuestion aggressionQuestion5 = surveyService.saveAggressionQuestion(
                AggressionQuestion.builder()
                        .content("보호자가 아이의 물건을 강제로 빼앗았나요?".getBytes())
                        .build()
        );
        AggressionQuestion aggressionQuestion6 = surveyService.saveAggressionQuestion(
                AggressionQuestion.builder()
                        .content("먹이는 부족했나요?".getBytes())
                        .build()
        );
        AggressionQuestion aggressionQuestion7 = surveyService.saveAggressionQuestion(
                AggressionQuestion.builder()
                        .content("보호자가 아이를 힘으로 통제하였나요?".getBytes())
                        .build()
        );

        AnxietyQuestion anxietyQuestion1 = surveyService.saveAnxietyQuestion(
                AnxietyQuestion.builder()
                        .content("대소변을 잘 가리지 못하나요?".getBytes())
                        .build()
        );
        AnxietyQuestion anxietyQuestion2 = surveyService.saveAnxietyQuestion(
                AnxietyQuestion.builder()
                        .content("지속해서 짖거나 하울링하나요?".getBytes())
                        .build()
        );
        AnxietyQuestion anxietyQuestion3 = surveyService.saveAnxietyQuestion(
                AnxietyQuestion.builder()
                        .content("씹기와 같은 파괴적인 행동을 하나요?".getBytes())
                        .build()
        );
        AnxietyQuestion anxietyQuestion4 = surveyService.saveAnxietyQuestion(
                AnxietyQuestion.builder()
                        .content("측대보를 하나요?".getBytes())
                        .build()
        );
        AnxietyQuestion anxietyQuestion5 = surveyService.saveAnxietyQuestion(
                AnxietyQuestion.builder()
                        .content("물이나 음식을 먹지 않나요?".getBytes())
                        .build()
        );
        AnxietyQuestion anxietyQuestion6 = surveyService.saveAnxietyQuestion(
                AnxietyQuestion.builder()
                        .content("자신의 발을 핥거나 상처를 입히나요?".getBytes())
                        .build()
        );
        AnxietyQuestion anxietyQuestion7 = surveyService.saveAnxietyQuestion(
                AnxietyQuestion.builder()
                        .content("식분증과 같은 이상행동을 보이나요?".getBytes())
                        .build()
        );
    }
}
