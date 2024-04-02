package com.ecommerce.paymentService.module;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table (name = "payments")

public class Payments {

    @Id
    private Integer paymentId;
    private Long orderId;
    private LocalDateTime paymentDate;
    private Double amount;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(value = EnumType.STRING)
    private ChangeStatus status;
}
