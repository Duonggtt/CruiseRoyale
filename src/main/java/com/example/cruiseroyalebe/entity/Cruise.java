package com.example.cruiseroyalebe.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;


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

    @ElementCollection
    @CollectionTable(name = "cruise_short_descs", joinColumns = @JoinColumn(name = "cruise_id"))
    @Column(name = "short_desc")
    private List<String> shortDesc = new ArrayList<>();

    private String description;
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "section_id")
    private List<CruiseDetailSection> sections;

    @Column(name = "owned_date")
    private Date ownedDate;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @ManyToMany(fetch = LAZY)
    private Collection<Rule> rules = new ArrayList<>();

    @ManyToMany(fetch = LAZY)
    private Collection<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
