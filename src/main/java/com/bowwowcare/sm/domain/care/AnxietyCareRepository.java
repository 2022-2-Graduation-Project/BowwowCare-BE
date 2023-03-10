package com.bowwowcare.sm.domain.care;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnxietyCareRepository extends JpaRepository<AnxietyCare, Long> {

    boolean existsAnxietyCareBySituationAndPetId(int situation, Long pet_id);

}
