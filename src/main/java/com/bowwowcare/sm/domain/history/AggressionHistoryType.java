package com.bowwowcare.sm.domain.history;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggressionHistoryType {

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
