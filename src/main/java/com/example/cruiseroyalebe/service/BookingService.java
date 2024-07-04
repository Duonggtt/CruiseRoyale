package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Booking;
import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.modal.dto.BookingDto;
import com.example.cruiseroyalebe.modal.request.UpdateBookingPaymentStatusRequest;
import com.example.cruiseroyalebe.modal.request.UpsertBookingRequest;
import com.example.cruiseroyalebe.modal.request.UpsertCabinRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public interface BookingService {
    Page<Booking> getAllBookings(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Booking> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    Booking createBooking(UpsertBookingRequest request);
    Booking updateBooking(Integer id, UpsertBookingRequest request);
    Booking getBookingById(Integer id);
    BookingDto getBookingDtoById(Integer id);
    void deleteBooking(Integer id);
    List<BookingDto> getBookings();
    Boolean returnBooking(Integer bookingId);
    BigDecimal getTotalRevenue();
    int getCountBooking();
    int getCountBookingByStatusTrue();
    int getCountBookingByStatusFalse();
    List<BookingDto> getAllBookingsByUser_NameLike(String name);
    Booking updateStatus(UpdateBookingPaymentStatusRequest request, Integer id);
}
