package com.example.cruiseroyalebe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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

    @ManyToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;

    @OneToMany
    @JoinColumn(name = "cruise_detail_section_id")
    private List<CruiseDtSectionImage> cruiseDtSectionImages;

}
