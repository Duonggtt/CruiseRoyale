package com.example.cruiseroyalebe.modal.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CruiseFeaturedDto {
    private Integer id;
    private String name;
    private BigDecimal price;
}
