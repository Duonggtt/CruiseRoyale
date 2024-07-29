package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}