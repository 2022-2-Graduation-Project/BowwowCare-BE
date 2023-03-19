package com.bowwowcare.sm.domain.pet;


import com.bowwowcare.sm.domain.enums.Gender;
import com.bowwowcare.sm.domain.user.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Lob
    @Column
    private byte[] petImage;

    private LocalDate birthDate;

    private LocalDate adoptionDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private Member member;


}
