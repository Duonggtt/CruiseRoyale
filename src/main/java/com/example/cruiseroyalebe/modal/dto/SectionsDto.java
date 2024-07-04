package com.example.cruiseroyalebe.modal.dto;

import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SectionsDto {
    private Integer id;
    private String text;
    private CruiseSectionDto cruiseDto;
    private Integer cruiseDtSectionImageId;
}
