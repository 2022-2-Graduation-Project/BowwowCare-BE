package com.bowwowcare.sm.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnxietyHistoryRepository extends JpaRepository<AnxietyHistory, Long> {

    @Query(nativeQuery = true, value = "select * from anxiety_history a where a.pet_id = :petId")
    List<AnxietyHistory> findAnxietyHistoryListByPet(@Param("petId") Long petId);
}
