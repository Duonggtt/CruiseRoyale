package com.example.cruiseroyalebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    @Column(name = "review_date")
    private Date reviewDate;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
