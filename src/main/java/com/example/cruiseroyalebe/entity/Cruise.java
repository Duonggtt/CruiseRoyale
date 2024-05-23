package com.example.cruiseroyalebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cruise")
public class Cruise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(name = "lauched_year")
    private int launchedYear;

    @Column(name = "cabin_quantity")
    private int cabinQuantity;

    private String material;
    private String description;
    private BigDecimal price;

    @ManyToMany(fetch = EAGER)
    private Collection<Rule> rules = new ArrayList<>();

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @ManyToMany(fetch = EAGER)
    private Collection<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
