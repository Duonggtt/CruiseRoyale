package com.example.cruiseroyalebe.modal.request;

import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertCruiseDetailSection {

    private String text;
    private Integer cruiseId;
    private Integer cruiseDtSectionImageId;

}
