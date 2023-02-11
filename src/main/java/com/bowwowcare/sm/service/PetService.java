package com.bowwowcare.sm.service;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.domain.enums.Gender;
import com.bowwowcare.sm.domain.pet.Pet;
import com.bowwowcare.sm.domain.pet.PetRepository;
import com.bowwowcare.sm.domain.user.MemberRepository;
import com.bowwowcare.sm.dto.pet.PetRegisterRequestDto;
import com.bowwowcare.sm.dto.pet.PetRegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PetRegisterResponseDto registerPet(PetRegisterRequestDto petRegisterRequestDto, MemberDetails memberDetails) {

        Pet pet = petRepository.save(
                Pet.builder()
                        .name(petRegisterRequestDto.getName())
                        .gender(Gender.valueOf(petRegisterRequestDto.getGender().toUpperCase()))
                        .petImg(petRegisterRequestDto.getPetImg())
                        .birthDate(petRegisterRequestDto.getBirthDate())
                        .adoptonDate(petRegisterRequestDto.getAdoptionDate())
                        .member(memberRepository.findByEmail(memberDetails.getUsername()).get())
                        .build()
        );

        return PetRegisterResponseDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .build();

    }
}
