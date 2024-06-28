package com.example.cruiseroyalebe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "room_quantity")
    private int roomQuantity;

    @Column(name = "available_rooms")
    private int availableRooms;

    @ManyToOne
    @JoinColumn(name = "cabin_type_id")
    private CabinType cabinType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;

}
