package com.ecommerce.paymentService.controller;

import com.ecommerce.paymentService.module.Payments;
import com.ecommerce.paymentService.payload.PaymentRequest;
import com.ecommerce.paymentService.payload.PaymentResponse;
import com.ecommerce.paymentService.service.serviceImpl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest){

        PaymentResponse paymentResponse= paymentService.processPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payments> getPaymentById(Integer paymentId){
        Payments getPaymentById = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(getPaymentById);
    }

}
