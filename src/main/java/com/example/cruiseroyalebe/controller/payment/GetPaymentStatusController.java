package com.example.cruiseroyalebe.controller.payment;

import com.example.cruiseroyalebe.modal.dto.StatusRequestDTO;
import com.example.cruiseroyalebe.service.payment.GetPaymentStatusService;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class GetPaymentStatusController {

    @Autowired
    private GetPaymentStatusService getPaymentStatusService;

    @PostMapping("/api/v1/get-status")
    public ResponseEntity<Map<String, Object>> getStatus(HttpServletRequest request, @RequestBody StatusRequestDTO statusRequestDTO) throws IOException {

        Map<String, Object> result = this.getPaymentStatusService.getStatus(request, statusRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
