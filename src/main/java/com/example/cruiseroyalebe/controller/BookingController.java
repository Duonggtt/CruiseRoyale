package com.example.cruiseroyalebe.controller;

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
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/bookings")
public class BookingController {
    public final BookingService bookingService;

    @GetMapping("/")
    public ResponseEntity<?> getAllBookings(@RequestParam(required = false, defaultValue = "id") String sortField,
                                          @RequestParam(required = false, defaultValue = "esc") String sortDirection,
                                          @RequestParam(required = false, defaultValue = "1") Integer page,
                                          @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(bookingService.getAllBookings(page, limit , sortField, sortDirection));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@Valid @RequestBody UpsertBookingRequest request) {
        return new ResponseEntity<>(bookingService.createBooking(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer id,@Valid @RequestBody UpsertBookingRequest request) {
        return new ResponseEntity<>(bookingService.updateBooking(id,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
    }
}
