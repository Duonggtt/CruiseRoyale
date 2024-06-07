package com.example.cruiseroyalebe.modal.dto;

import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.entity.Rule;
import com.example.cruiseroyalebe.entity.Tag;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CruiseDto {
    private Integer id;
    private String name;
    private int launchedYear;
    private int cabinQuantity;
    private String material;
    private String description;
    private BigDecimal price;
    private Date ownedDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private List<Integer> ruleIds;
    private List<Integer> tagIds;
    private Integer locationId;
    private Integer ownerId;
    private List<Integer> imageIds;
    private Integer reviewId;
}
