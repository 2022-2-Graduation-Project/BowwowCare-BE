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
@Table(name = "anxiety_result")
@Builder
public class AnxietyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(length=100000)
    private byte[] situation;

    @Lob
    @Column(length=100000)
    private byte[] solution1;

    @Lob
    @Column(length=100000)
    private byte[] solution2;

    @Lob
    @Column(length=100000)
    private byte[] solution3;

    public AnxietyResult(byte[] situation, byte[] solution1) {
        this.situation = situation;
        this.solution1 = solution1;
    }
}
