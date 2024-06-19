package com.example.cruiseroyalebe.modal.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertCruiseRequest {
    private String name;
    private int launchedYear;
    private int cabinQuantity;
    private String material;
    private String description;
    private BigDecimal price;
    private Date ownedDate;
    List<String> shortDesc;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer locationId;
    private List<Integer> ruleIds;
    private List<Integer> tagIds;
    private Integer ownerId;
}
