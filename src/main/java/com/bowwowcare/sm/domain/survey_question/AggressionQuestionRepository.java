package com.bowwowcare.sm.domain.survey_question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AggressionQuestionRepository extends JpaRepository<AggressionQuestion, Long> {

    AggressionQuestion findAggressionQuestionByContent(byte[] content);

}
