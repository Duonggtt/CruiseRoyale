package com.example.cruiseroyalebe.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "route_name")
    private String routeName;

    private String address;
    private String city;
}
