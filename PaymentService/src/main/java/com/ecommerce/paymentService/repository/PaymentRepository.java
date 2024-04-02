package com.ecommerce.paymentService.repository;

import com.ecommerce.paymentService.module.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository <Payments, Integer> {
}
