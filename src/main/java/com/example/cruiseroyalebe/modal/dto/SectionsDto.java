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
    private String text;
    private Integer cruiseId;
    private Collection<CruiseDtSectionImage> cruiseDtSectionImages;
}
