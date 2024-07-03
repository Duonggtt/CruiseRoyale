package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findById(Integer id);

    @Query("select b from Booking b where "
            + "concat(b.id, b.user.name)"
            + "LIKE %?1%")
    Page<Booking> findAll(String keyword, Pageable pageable);

    Booking findByCruiseId(Integer cruiseId);

    boolean existsByUserId(Integer userId);

    @Query("select sum(b.totalPrice) from Booking b where b.paymentStatus = true")
    BigDecimal getTotalRevenue();

    @Query("select count(b) from Booking b")
    int countBooking();

    @Query("select count(b) from Booking b where b.bookingStatus = true")
    int countBookingByBookingStatusTrue();

    @Query("select count(b) from Booking b where b.bookingStatus = false")
    int countBookingByBookingStatusFalse();

    @Query("select b from Booking b where b.user.name like %?1%")
    List<Booking> findAllByUser_NameLike(String name);
}
