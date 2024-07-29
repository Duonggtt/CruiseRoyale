package com.example.cruiseroyalebe.controller;

import com.example.cruiseroyalebe.modal.request.PaymentRequest;
import com.example.cruiseroyalebe.modal.respone.PaymentResponse;
import com.example.cruiseroyalebe.service.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class PaymentController {

    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequest request) {
        String paymentUrl = vnPayService.createPayment(request);
        return ResponseEntity.ok(paymentUrl);
    }

    @GetMapping("/callback")
    public ResponseEntity<PaymentResponse> paymentCallback(@RequestParam Map<String, String> queryParams) {
        PaymentResponse response = vnPayService.processPaymentResponse(queryParams);
        return ResponseEntity.ok(response);
    }
}