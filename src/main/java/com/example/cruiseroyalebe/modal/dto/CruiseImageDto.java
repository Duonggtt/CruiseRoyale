package com.example.cruiseroyalebe.modal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CruiseImageDto {
    private Integer id;
    private byte[] data;
    private String type;
    private String createdAt;
    private Integer cruiseId;
}
