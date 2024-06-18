package com.example.cruiseroyalebe.modal.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertBookingRequest {
    private Date bookingDate;
    private int guestQuantity;
    private String note;
    private Boolean bookingStatus;
    private Boolean paymentStatus;
    private Integer userId;
    private List<Integer> cabinIds;
    private Integer cruiseId;
}
