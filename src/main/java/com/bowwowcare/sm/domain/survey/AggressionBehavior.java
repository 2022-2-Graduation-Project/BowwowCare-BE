package com.bowwowcare.sm.domain.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aggression_behavior")
@Builder
public class AggressionBehavior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private int aggressionType;


    @Lob
    @Column(length=100000)
    private byte[] content;
}
