package com.bowwowcare.sm.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByName(String name);

    @Query(nativeQuery = true, value = "select * from Pet p where p.user_id = :memberId")
    List<Pet> findPetListByMember(@Param("memberId") Long memberId);
}
