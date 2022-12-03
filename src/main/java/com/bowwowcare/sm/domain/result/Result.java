package com.bowwowcare.sm.domain.result;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Result {

    //감정 분석 결과 저장

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String result; //happy, sad, angry, relaxed

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

     */

    @Builder
    public Result(String result) {

        this.result = result;
    }
}
