package com.bowwowcare.sm.domain.user;

import com.bowwowcare.sm.domain.user.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean theme1;

    @Column
    private boolean theme2;

    @Column
    private boolean theme3;

    @Column
    private boolean theme4;

    @Column
    private boolean theme5;

    @Column
    private boolean theme6;

    @Column
    private boolean theme7;

    @Column
    private boolean theme8;

    @Column
    private boolean theme9;

    @Column
    private boolean theme10;
}
