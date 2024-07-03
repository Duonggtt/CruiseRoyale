package com.example.cruiseroyalebe.modal.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateBookingPaymentStatusRequest {
    private Boolean paymentStatus;
}
