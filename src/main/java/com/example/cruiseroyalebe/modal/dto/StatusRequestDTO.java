package com.example.cruiseroyalebe.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequestDTO {

    private String orderId;
    private String orderInfo;
    private String transactionNo;
    private String transDate;
    private Long amount;
}
