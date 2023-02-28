package com.bowwowcare.sm.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AggressionHistoryRepository extends JpaRepository<AggressionHistory,Long> {

    @Query(nativeQuery = true, value = "select * from aggression_history a where a.pet_id = :petId")
    List<AggressionHistory> findAggressionHistoryListByPet(@Param("petId") Long petId);
}
