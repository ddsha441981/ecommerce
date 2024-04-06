package com.ecommerce.orderService.payload;

import com.ecommerce.orderService.model.ChangeStatus;
import com.ecommerce.orderService.model.OrderItem;
import com.ecommerce.orderService.model.Orders;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderResponse {
    private int orderId;
    private int userId;
    private String orderDate;
    private double totalAmount;
    private ChangeStatus status;
    private List<OrderItem> items;

    // Static method to convert Orders to OrderResponse
    public static OrderResponse fromOrder(Orders orders) {
        return OrderResponse.builder()
                .orderId(orders.getOrderId())
                .userId(orders.getUserId())
                .orderDate(orders.getOrderDate())
                .items(orders.getItems())
                .totalAmount(orders.getTotalAmount())
                .status(orders.getStatus())
                .build();
    }

    // Static method to convert Orders to OrderResponse
//    public static OrderResponse fromOrder(Orders orders) {
//        return OrderResponse.builder()
//                .orderId(orders.getOrderId())
//                .userId(orders.getUserId())
//                .orderDate(orders.getOrderDate())
//                .items(orders.getItems())
//                .totalAmount(orders.getTotalAmount())
//                .status(orders.getStatus())
//                .build();
//    }
}
