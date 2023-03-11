package com.bowwowcare.sm.domain.survey_result;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnxietyResultRepository extends JpaRepository<AnxietyResult, Long> {

    AnxietyResult findAnxietyResultBySituation(byte[] situation);

}
