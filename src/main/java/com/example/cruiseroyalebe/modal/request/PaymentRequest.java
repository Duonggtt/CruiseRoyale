package com.example.cruiseroyalebe.modal.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private long amount;
    private String orderInfo;
}