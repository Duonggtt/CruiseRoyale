package com.example.cruiseroyalebe.entity;

import lombok.*;
import javax.persistence.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cabin_type")
public class CabinType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "room_size")
    private int roomSize;

    @Column(name = "max_guests")
    private int maxGuests;

    private String description;
    private BigDecimal price;

}
