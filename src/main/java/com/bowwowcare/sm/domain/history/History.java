package com.bowwowcare.sm.domain.history;

import com.bowwowcare.sm.domain.BaseTimeEntity;
import com.bowwowcare.sm.domain.enums.Type;
import com.bowwowcare.sm.domain.pet.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //공격행동에 대한 건지 분리불안에 대한 건지 판별
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    //상황 번호(id)
    @Column(nullable = false)
    private int situation;

    @Column
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petId")
    private Pet pet;

    @Builder
    public History(Type type, int situation, LocalDate createdDate, Pet pet){
        this.type = type;
        this.situation = situation;
        this.createdDate = createdDate;
        this.pet = pet;
    }
}
