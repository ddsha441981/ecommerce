package com.ecommerce.paymentService.service;

import com.ecommerce.paymentService.module.Payments;
import com.ecommerce.paymentService.payload.PaymentRequest;
import com.ecommerce.paymentService.payload.PaymentResponse;

public interface PaymentService {

    PaymentResponse processPayment (PaymentRequest paymentRequest);
    Payments getPaymentById (Integer paymentId);

}
