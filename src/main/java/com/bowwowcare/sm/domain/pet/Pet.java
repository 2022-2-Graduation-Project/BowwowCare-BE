package com.bowwowcare.sm.domain.pet;


import com.bowwowcare.sm.domain.enums.Gender;
import com.bowwowcare.sm.domain.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    */

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private String petImg;

    private LocalDate birthDate;

    private LocalDate adoptionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member member;

    /*@Enumerated(EnumType.STRING)
    private Stastus stastus = Stastus.ACTIVE;*/

    @Builder
    public Pet(String name, Gender gender, String petImg, LocalDate birthDate, LocalDate adoptonDate, Member member) {

        this.name = name;
        this.gender = gender;
        this.petImg = petImg;
        this.birthDate = birthDate;
        this.adoptionDate = adoptonDate;
        this.member = member;
    }
}
