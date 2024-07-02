package com.example.cruiseroyalebe.modal.dto;

import com.example.cruiseroyalebe.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDto {
    private Integer id;
    private Date bookingDate;
    private Date orderDate;
    private int guestQuantity;
    private BigDecimal totalPrice;
    private String note;
    private Boolean bookingStatus;
    private Boolean paymentStatus;
    private UserDto userDto;
    private List<CabinDto> cabinDto;
    private CruiseBookingDto cruiseDto;
}
