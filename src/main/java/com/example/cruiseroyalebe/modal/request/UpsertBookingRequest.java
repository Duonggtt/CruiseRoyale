package com.example.cruiseroyalebe.modal.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertBookingRequest {
    private Date bookingDate;
    private Date orderDate;
    private int guestQuantity;
    private String note;
    private Boolean bookingStatus;
    private Boolean paymentStatus;
    private Integer userId;
    private Integer cabinId;
    private Integer cruiseId;
}
