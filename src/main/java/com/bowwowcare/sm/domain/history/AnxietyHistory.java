package com.bowwowcare.sm.domain.history;

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
@Table(name = "anxiety_history")
public class AnxietyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean situation1;

    @Column(nullable = false)
    private boolean situation2;

    @Column(nullable = false)
    private boolean situation3;

    @Column(nullable = false)
    private boolean situation4;

    @Column(nullable = false)
    private boolean situation5;

    @Column(nullable = false)
    private boolean situation6;

    @Column(nullable = false)
    private boolean situation7;

    @Column
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petId")
    private Pet pet;

    @Builder
    public AnxietyHistory(boolean situation1, boolean situation2, boolean situation3, boolean situation4,
                                         boolean situation5, boolean situation6, boolean situation7, LocalDate createdDate, Pet pet){

        this.situation1 = situation1;
        this.situation2 = situation2;
        this.situation3 = situation3;
        this.situation4 = situation4;
        this.situation5 = situation5;
        this.situation6 = situation6;
        this.situation7 = situation7;
        this.createdDate = createdDate;
        this.pet = pet;
    }
}
