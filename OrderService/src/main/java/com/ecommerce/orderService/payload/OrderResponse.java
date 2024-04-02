package com.ecommerce.orderService.payload;

import com.ecommerce.orderService.model.ChangeStatus;
import com.ecommerce.orderService.model.Orders;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private long orderId;
    private long userId;
    private String orderDate;
//    private List<Long> productIds;
    private double totalAmount;
    private ChangeStatus status;


    // Static method to convert Orders to OrderResponse
    public static OrderResponse fromOrder(Orders orders) {
        return OrderResponse.builder()
                .orderId(orders.getOrderId())
                .userId(orders.getUserId())
                .orderDate(orders.getOrderDate())
//                .productIds(orders.getProductIds())
                .totalAmount(orders.getTotalAmount())
                .status(orders.getStatus())
                .build();
    }
}
