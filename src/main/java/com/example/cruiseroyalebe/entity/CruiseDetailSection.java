package com.example.cruiseroyalebe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "cruise_detail_section_id")
    private List<CruiseDtSectionImage> cruiseDtSectionImages;

}
