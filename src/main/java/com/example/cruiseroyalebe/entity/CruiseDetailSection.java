package com.example.cruiseroyalebe.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cruise_detail_section")
public class CruiseDetailSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Lob
    @Column(nullable = true)
    private byte[] image;

    @Column(nullable = true)
    private String imageFileName;
}
