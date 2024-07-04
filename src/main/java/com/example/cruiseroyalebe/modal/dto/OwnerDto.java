package com.example.cruiseroyalebe.modal.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OwnerDto {
    private Integer id;
    private String name;
    private List<String> CruiseNames;
}
