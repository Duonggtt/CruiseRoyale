package com.example.cruiseroyalebe.entity;

import jakarta.persistence.*;
import lombok.*;

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
