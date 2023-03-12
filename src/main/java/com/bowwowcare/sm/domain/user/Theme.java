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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member member;
}
