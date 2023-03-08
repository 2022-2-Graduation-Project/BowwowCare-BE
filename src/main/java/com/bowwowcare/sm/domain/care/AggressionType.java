package com.bowwowcare.sm.domain.care;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggressionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean type0;

    @Column
    private boolean type1;

    @Column
    private boolean type2;
}
