package com.bowwowcare.sm.domain.care;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnxietyCareRepository extends JpaRepository<AnxietyCare, Long> {

    boolean existsAnxietyCareBySituationAndPetId(int situation, Long pet_id);

    List<AnxietyCare> findAllByPetId(Long pet_id);

    AnxietyCare findAnxietyCareBySituationAndPetId(int situation, Long pet_id);

}
