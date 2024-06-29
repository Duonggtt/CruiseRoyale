package com.example.cruiseroyalebe.controller.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class CallbackPaymentController {

    @GetMapping("/api/v1/callback")
    public ResponseEntity<Map<String, Object>> doCallBack(@RequestParam Map<String, Object> callBackInfo) {
        System.out.println("Received callback data: " + callBackInfo);
        // Xử lý dữ liệu và trả về response
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Payment processed successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
