package com.bowwowcare.sm.service;

import com.bowwowcare.sm.config.security.member.MemberDetails;
import com.bowwowcare.sm.domain.enums.Gender;
import com.bowwowcare.sm.domain.pet.Pet;
import com.bowwowcare.sm.domain.pet.PetRepository;
import com.bowwowcare.sm.domain.user.MemberRepository;
import com.bowwowcare.sm.dto.pet.PetInfoResponseDto;
import com.bowwowcare.sm.dto.pet.PetListResponseDto;
import com.bowwowcare.sm.dto.pet.PetRegisterRequestDto;
import com.bowwowcare.sm.dto.pet.PetRegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
                        .petImage(petRegisterRequestDto.getPetImg().getBytes())
                        .birthDate(petRegisterRequestDto.getBirthDate())
                        .adoptionDate(petRegisterRequestDto.getAdoptionDate())
                        .member(memberRepository.findByEmail(memberDetails.getUsername()).get())
                        .build()
        );

        return PetRegisterResponseDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .build();

    }


    public List<PetListResponseDto> findPetListByMember(MemberDetails memberDetails) {

        List<PetListResponseDto> finalPetList = new ArrayList<>();

        Long memId = memberRepository.findByEmail(memberDetails.getUsername()).get().getId();
        List<Pet> petList = petRepository.findPetListByMember(memId);

        for (Pet pet : petList) {
            PetListResponseDto p = PetListResponseDto.builder()
                    .petId(pet.getId())
                    .petName(pet.getName())
                    .petImage(new String(pet.getPetImage(), StandardCharsets.UTF_8))
                    .build();

            finalPetList.add(p);
        }

        return finalPetList;
    }


    public PetInfoResponseDto findPetById(int petId) {

        Pet pet = petRepository.getOne((long) petId);

        return PetInfoResponseDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .petImg(new String(pet.getPetImage(), StandardCharsets.UTF_8))
                .gender(pet.getGender().toString())
                .birthDate(pet.getBirthDate())
                .adoptionDate(pet.getAdoptionDate())
                .memberId(pet.getMember().getId())
                .build();
    }
}
