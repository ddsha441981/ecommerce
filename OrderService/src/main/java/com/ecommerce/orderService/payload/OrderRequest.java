package com.ecommerce.orderService.payload;

import com.ecommerce.orderService.model.ChangeStatus;
import com.ecommerce.orderService.model.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    private int orderId;
    private int userId;
    private String orderDate;
    private double totalAmount;
    private ChangeStatus status;
    private List<OrderItem> items;
}
