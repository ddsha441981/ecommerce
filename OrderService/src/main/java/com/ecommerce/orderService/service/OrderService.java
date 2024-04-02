package com.ecommerce.orderService.service;

import com.ecommerce.orderService.model.Orders;
import com.ecommerce.orderService.payload.OrderRequest;
import com.ecommerce.orderService.payload.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    Orders updateOrder(Long orderId, OrderRequest updatedOrderRequest);
    Optional<OrderResponse> getOrderById(Long orderId);
    List<OrderResponse> getAllOrders();
    void deleteOrder(Long orderId);

}
