package com.example.cruiseroyalebe.entity;

import lombok.*;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.LAZY;

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

    @ManyToMany(fetch = LAZY)
    private Collection<Tag> tags = new ArrayList<>();

}
