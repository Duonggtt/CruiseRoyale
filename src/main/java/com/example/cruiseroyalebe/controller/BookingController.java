package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.modal.request.UpdateBookingPaymentStatusRequest;
import com.example.cruiseroyalebe.modal.request.UpsertBookingRequest;
import com.example.cruiseroyalebe.modal.request.UpsertCabinRequest;
import com.example.cruiseroyalebe.service.BookingService;
import com.example.cruiseroyalebe.service.CabinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/bookings")
public class BookingController {
    public final BookingService bookingService;

    @GetMapping("/")
    public ResponseEntity<?> getBookings() {
        return ResponseEntity.ok(bookingService.getBookings());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@Valid @RequestBody UpsertBookingRequest request) {
        return new ResponseEntity<>(bookingService.createBooking(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer id,@Valid @RequestBody UpsertBookingRequest request) {
        return new ResponseEntity<>(bookingService.updateBooking(id,request), HttpStatus.CREATED);
    }

    @PutMapping("/check-out/{id}")
    public ResponseEntity<?> checkOutBooking(@PathVariable Integer id) {
        return new ResponseEntity<>(bookingService.returnBooking(id), HttpStatus.CREATED);
    }

    @GetMapping("/total-revenue")
    public ResponseEntity<?> getTotalRevenue() {
        return ResponseEntity.ok(bookingService.getTotalRevenue());
    }

    @GetMapping("/count-booking")
    public ResponseEntity<?> getCountBookings() {
        return ResponseEntity.ok(bookingService.getCountBooking());
    }

    @GetMapping("/count-booking-true")
    public ResponseEntity<?> getCountBookingsByStatusTrue() {
        return ResponseEntity.ok(bookingService.getCountBookingByStatusTrue());
    }

    @GetMapping("/count-booking-false")
    public ResponseEntity<?> getCountBookingsByStatusFalse() {
        return ResponseEntity.ok(bookingService.getCountBookingByStatusFalse());
    }

    @GetMapping("/search-by-user")
    public ResponseEntity<?> getBookingsByUser_NameLike(@RequestParam String name) {
        return ResponseEntity.ok(bookingService.getAllBookingsByUser_NameLike(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getBookingDtoById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getBookingDtoById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
    }

    @PutMapping("/update-payment-status/{id}")
    public ResponseEntity<?> updateStatus(@Valid @RequestBody UpdateBookingPaymentStatusRequest request, @PathVariable Integer id) {
        return new ResponseEntity<>(bookingService.updateStatus(request, id), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(bookingService.getBookingDtoByUserId(userId));
    }

    @GetMapping("/total-price/{bookingId}")
    public ResponseEntity<?> getTotalPriceByBookingId(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(bookingService.getTotalPriceByBookingId(bookingId));
    }
}
