package com.bowwowcare.sm.domain.survey_result;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AggressionResultRepository extends JpaRepository<AggressionResult, Long> {

    AggressionResult findAggressionResultBySituation(byte[] situation);

}
