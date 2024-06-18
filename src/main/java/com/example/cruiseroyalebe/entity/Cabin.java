package com.example.cruiseroyalebe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;
}
