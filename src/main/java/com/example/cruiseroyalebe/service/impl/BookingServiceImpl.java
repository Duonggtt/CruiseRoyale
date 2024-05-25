package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Booking;
import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.modal.request.UpsertBookingRequest;
import com.example.cruiseroyalebe.repository.BookingRepository;
import com.example.cruiseroyalebe.repository.CabinRepository;
import com.example.cruiseroyalebe.repository.CruiseRepository;
import com.example.cruiseroyalebe.repository.UserRepository;
import com.example.cruiseroyalebe.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CabinRepository cabinRepository;
    private final CruiseRepository cruiseRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Booking> getAllBookings(Integer page, Integer limit, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return bookingRepository.findAll(pageRequest);
    }

    @Override
    public Page<Booking> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Booking> bookingPage;
        if(keyword != null) {
            bookingPage = bookingRepository.findAll(keyword,pageable);
        }else {
            bookingPage = bookingRepository.findAll(pageable);
        }

        List<Booking> bookingList = bookingPage.getContent();

        return new PageImpl<>(bookingList, pageable, bookingPage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Booking createBooking(UpsertBookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cabin cabin = cabinRepository.findById(request.getCabinId())
                .orElseThrow(() -> new RuntimeException("Cabin not found"));

        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        Booking booking = new Booking();
        booking.setBookingDate(request.getBookingDate());
        booking.setOrderDate(request.getOrderDate());
        booking.setGuestQuantity(request.getGuestQuantity());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setNote(request.getNote());
        booking.setBookingStatus(request.getBookingStatus());
        booking.setPaymentStatus(request.getPaymentStatus());
        booking.setUser(user);
        booking.setCabin(cabin);
        booking.setCruise(cruise);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking updateBooking(Integer id, UpsertBookingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cabin cabin = cabinRepository.findById(request.getCabinId())
                .orElseThrow(() -> new RuntimeException("Cabin not found"));

        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));

        booking.setBookingDate(request.getBookingDate());
        booking.setOrderDate(request.getOrderDate());
        booking.setGuestQuantity(request.getGuestQuantity());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setNote(request.getNote());
        booking.setBookingStatus(request.getBookingStatus());
        booking.setPaymentStatus(request.getPaymentStatus());
        booking.setUser(user);
        booking.setCabin(cabin);
        booking.setCruise(cruise);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }

    @Override
    public void deleteBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }
}
