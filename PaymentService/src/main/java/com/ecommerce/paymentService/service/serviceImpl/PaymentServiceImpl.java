package com.ecommerce.paymentService.service.serviceImpl;

import com.ecommerce.paymentService.module.PaymentMethod;
import com.ecommerce.paymentService.module.Payments;
import com.ecommerce.paymentService.payload.PaymentRequest;
import com.ecommerce.paymentService.payload.PaymentResponse;
import com.ecommerce.paymentService.repository.PaymentRepository;
import com.ecommerce.paymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // Logic to process payment and save to database
    Payments payments = Payments.builder()
            .orderId(paymentRequest.getOrderId())
            .paymentDate(LocalDateTime.now())
            .amount(paymentRequest.getAmount())
            .paymentMethod(paymentRequest.getPaymentMethod())
            .status(paymentRequest.getStatus())
            .build();

    paymentRepository.save(payments);
        // Create and return a response
        return PaymentResponse.builder()
                .paymentId(payments.getPaymentId())
                .orderId(payments.getOrderId())
                .paymentDate(payments.getPaymentDate())
                .amount(payments.getAmount())
                .paymentMethod(payments.getPaymentMethod())
                .status(payments.getStatus())
                .build();
    }

    @Override
    public Payments getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(()-> new RuntimeException("Payment Not Found"));
    }
}
