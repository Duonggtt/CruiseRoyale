package com.example.cruiseroyalebe.modal.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertReviewRequest {
    private String comment;
    private int rating;
}
