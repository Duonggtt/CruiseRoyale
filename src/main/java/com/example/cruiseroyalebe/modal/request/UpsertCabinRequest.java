package com.example.cruiseroyalebe.modal.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertCabinRequest {
    private int roomQuantity;
    private Integer cabinTypeId;
    private Integer cruiseId;
}
