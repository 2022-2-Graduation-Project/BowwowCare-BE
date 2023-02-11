package com.bowwowcare.sm.domain.survey_result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aggression_result")
@Builder
public class AggressionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(length=100000)
    private byte[] situation;

    @Lob
    @Column(length=100000)
    private byte[] solution;

    public AggressionResult(byte[] situation, byte[] solution) {
        this.situation = situation;
        this.solution = solution;
    }
}
