package com.example.cruiseroyalebe.modal.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertBookingRequest {
    private Date bookingDate;
    private int guestQuantity;
    private Map<Integer, Integer> cabinBookings;
    private String note;
    private Boolean bookingStatus;
    private Boolean paymentStatus;
    private Integer userId;
    private Integer cruiseId;
}
