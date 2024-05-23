package com.example.cruiseroyalebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "guest_quantity")
    private int GuestQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    private String note;
    @Column(name = "booking_status")
    private Boolean bookingStatus;

    @Column(name = "payment_status")
    private Boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cabin_id")
    private Cabin cabin;

    @ManyToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;

}
