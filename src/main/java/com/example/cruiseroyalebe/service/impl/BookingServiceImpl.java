package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Booking;
import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.modal.dto.*;
import com.example.cruiseroyalebe.modal.request.UpdateBookingPaymentStatusRequest;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        Booking booking = new Booking();
        booking.setBookingDate(request.getBookingDate());
        booking.setOrderDate(new Date());
        booking.setGuestQuantity(request.getGuestQuantity());

        List<Cabin> bookedCabins = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Integer, Integer> entry : request.getCabinBookings().entrySet()) {
            Integer cabinId = entry.getKey();
            Integer roomsToBook = entry.getValue();

            Cabin cabin = cabinRepository.findById(cabinId)
                    .orElseThrow(() -> new RuntimeException("Cabin not found"));

            if (cabin.getAvailableRooms() < roomsToBook) {
                throw new RuntimeException("Not enough rooms for cabin " + cabinId);
            }

            cabin.setAvailableRooms(cabin.getAvailableRooms() - roomsToBook);
            cabinRepository.save(cabin);

            for (int i = 0; i < roomsToBook; i++) {
                bookedCabins.add(cabin);
            }

            totalPrice = totalPrice.add(cabin.getCabinType().getPrice().multiply(BigDecimal.valueOf(roomsToBook)));
        }

        booking.setTotalPrice(totalPrice);
        booking.setNote(request.getNote());
        booking.setBookingStatus(request.getBookingStatus());
        booking.setPaymentStatus(request.getPaymentStatus());
        booking.setUser(user);
        booking.setCabin(bookedCabins);
        booking.setCruise(cruise);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Integer id, UpsertBookingRequest request) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        // Trả lại phòng đã đặt trước đó
        for (Cabin cabin : existingBooking.getCabin()) {
            cabin.setAvailableRooms(cabin.getAvailableRooms() + 1);
            cabinRepository.save(cabin);
        }

        List<Cabin> bookedCabins = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        // Đặt phòng mới
        for (Map.Entry<Integer, Integer> entry : request.getCabinBookings().entrySet()) {
            Integer cabinId = entry.getKey();
            Integer roomsToBook = entry.getValue();

            Cabin cabin = cabinRepository.findById(cabinId)
                    .orElseThrow(() -> new RuntimeException("Cabin not found"));

            if (cabin.getAvailableRooms() < roomsToBook) {
                throw new RuntimeException("Not enough rooms for cabin " + cabinId);
            }

            cabin.setAvailableRooms(cabin.getAvailableRooms() - roomsToBook);
            cabinRepository.save(cabin);

            for (int i = 0; i < roomsToBook; i++) {
                bookedCabins.add(cabin);
            }

            totalPrice = totalPrice.add(cabin.getCabinType().getPrice().multiply(BigDecimal.valueOf(roomsToBook)));
        }

        existingBooking.setBookingDate(request.getBookingDate());
        existingBooking.setGuestQuantity(request.getGuestQuantity());
        existingBooking.setTotalPrice(totalPrice);
        existingBooking.setNote(request.getNote());
        existingBooking.setBookingStatus(request.getBookingStatus());
        existingBooking.setPaymentStatus(request.getPaymentStatus());
        existingBooking.setUser(user);
        existingBooking.setCabin(bookedCabins);
        existingBooking.setCruise(cruise);

        return bookingRepository.save(existingBooking);
    }

    @Override
    public Boolean returnBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if(!booking.getPaymentStatus()) {
            throw new RuntimeException("Booking has not been paid yet");
        }

        for (Cabin cabin : booking.getCabin()) {
            cabin.setAvailableRooms(cabin.getAvailableRooms() + 1);
            cabinRepository.save(cabin);
        }

        booking.setBookingStatus(false);
        bookingRepository.save(booking);
        return true;
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
    public List<BookingDto> getBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public BigDecimal getTotalRevenue() {
        return bookingRepository.getTotalRevenue();
    }

    @Override
    public int getCountBooking() {
        return bookingRepository.countBooking();
    }

    @Override
    public int getCountBookingByStatusTrue() {
        return bookingRepository.countBookingByBookingStatusTrue();
    }

    @Override
    public int getCountBookingByStatusFalse() {
        return bookingRepository.countBookingByBookingStatusFalse();
    }

    @Override
    public List<BookingDto> getAllBookingsByUser_NameLike(String name) {
        return bookingRepository.findAllByUser_NameLike(name).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public Booking updateStatus(UpdateBookingPaymentStatusRequest request, Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        booking.setPaymentStatus(request.getPaymentStatus());
        bookingRepository.save(booking);
        return booking;
    }

    public BookingDto convertToDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setBookingDate(booking.getBookingDate());
        bookingDto.setOrderDate(booking.getOrderDate());
        bookingDto.setGuestQuantity(booking.getGuestQuantity());
        bookingDto.setTotalPrice(booking.getTotalPrice());
        bookingDto.setNote(booking.getNote());
        bookingDto.setBookingStatus(booking.getBookingStatus());
        bookingDto.setPaymentStatus(booking.getPaymentStatus());

        // Convert User to UserDto
        if (booking.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(booking.getUser().getId());
            userDto.setName(booking.getUser().getName());
            userDto.setPhone(booking.getUser().getPhone());
            userDto.setEmail(booking.getUser().getEmail());
            bookingDto.setUserDto(userDto);
        }

        // Convert Cruise to CruiseBookingDto
        if (booking.getCruise() != null) {
            CruiseBookingDto cruiseDto = new CruiseBookingDto();
            cruiseDto.setId(booking.getCruise().getId());
            cruiseDto.setName(booking.getCruise().getName());
            bookingDto.setCruiseDto(cruiseDto);
        }

        // Convert Cabins to List<CabinDto> including CabinTypeDto
        if (booking.getCabin() != null) {
            List<CabinDto> cabinDtos = booking.getCabin().stream()
                    .map(cabin -> {
                        CabinDto cabinDto = new CabinDto();
                        cabinDto.setId(cabin.getId());

                        if (cabin.getCabinType() != null) {
                            CabinTypeDto cabinTypeDto = new CabinTypeDto();
                            cabinTypeDto.setId(cabin.getCabinType().getId());
                            cabinTypeDto.setName(cabin.getCabinType().getName());
                            cabinDto.setCabinTypeDto(cabinTypeDto);
                        }

                        return cabinDto;
                    })
                    .toList();
            bookingDto.setCabinDto(cabinDtos);
        }

        return bookingDto;
    }
}
