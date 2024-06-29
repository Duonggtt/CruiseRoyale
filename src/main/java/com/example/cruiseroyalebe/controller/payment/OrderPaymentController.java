package com.example.cruiseroyalebe.controller.payment;

import com.example.cruiseroyalebe.modal.dto.OrderRequestDTO;
import com.example.cruiseroyalebe.service.payment.OrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class OrderPaymentController {

    @Autowired
    private OrderPaymentService orderPaymentService;

    @PostMapping("/api/v1/create-order")
    public ResponseEntity<Map<String, Object>> createOrderPayment(HttpServletRequest request, @RequestBody OrderRequestDTO orderRequestDTO) throws IOException {

        Map<String, Object> result = this.orderPaymentService.createOrder(request, orderRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
