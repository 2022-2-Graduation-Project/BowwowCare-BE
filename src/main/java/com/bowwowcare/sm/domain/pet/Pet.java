package com.bowwowcare.sm.domain.pet;


import com.bowwowcare.sm.domain.enums.Gender;
import com.bowwowcare.sm.domain.enums.Stastus;
import com.bowwowcare.sm.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    */

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private String petImgUrl;

    private LocalDate birthday;

    private LocalDate adoptionDay;

    @Enumerated(EnumType.STRING)
    private Stastus stastus = Stastus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
