package com.ecommerce.orderService.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long userId;
    private String orderDate;
//    private List<Long> productIds;
    private double totalAmount;
    @Enumerated(EnumType.STRING)
    private ChangeStatus status;

}
