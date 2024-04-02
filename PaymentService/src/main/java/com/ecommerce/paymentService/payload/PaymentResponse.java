package com.ecommerce.paymentService.payload;

import com.ecommerce.paymentService.module.ChangeStatus;
import com.ecommerce.paymentService.module.PaymentMethod;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class PaymentResponse {

        private Integer paymentId;
        private Long orderId;
        private LocalDateTime paymentDate;
        private Double amount;
        private PaymentMethod paymentMethod;
        private ChangeStatus status;
    }

