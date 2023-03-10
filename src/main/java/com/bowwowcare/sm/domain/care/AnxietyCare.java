package com.bowwowcare.sm.domain.care;

import com.bowwowcare.sm.domain.pet.Pet;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnxietyCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int count;

    @Column
    private LocalDate missionDate;

    /*
    situation
    - 1 : 외출하기 전
    - 2 : 훈련
    - 3 : 외출 후
     */
    @Column
    private int situation;

    @Column
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petId")
    private Pet pet;
}
