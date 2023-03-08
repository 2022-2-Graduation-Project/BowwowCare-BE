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
public class AggressionCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int count;

    @Column
    private LocalDate missionDate;

    @Column
    private int solution;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aggression_type")
    private AggressionType aggressionType;

    @Column
    private LocalDate modifiedAt;

    @Column
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petId")
    private Pet pet;
}
