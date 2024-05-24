package com.example.cruiseroyalebe.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cabin")
public class Cabin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cabin_type_id")
    private CabinType cabinType;

    @ManyToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;
}
