package com.ecommerce.orderService.payload;

import com.ecommerce.orderService.model.ChangeStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    private long orderId;
    private long userId;
    private String orderDate;
//    private List<Long> productIds;
    private double totalAmount;
    private ChangeStatus status;
}
