package com.bowwowcare.sm.domain.care;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AggressionCareRepository extends JpaRepository<AggressionCare, Long> {

    boolean existsAggressionCareBySolutionAndPetId(int solution, Long pet_id);


    @Query(nativeQuery = true, value = "select * from aggression_care a where a.solution = :solution and a.pet_id = :petId")
    AggressionCare findAggressionCareBySolutionAndPetId(@Param("solution") int solution, @Param("petId") Long petId);
}
