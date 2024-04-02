package com.ecommerce.orderService.service.serviceImpl;

import com.ecommerce.orderService.model.ChangeStatus;
import com.ecommerce.orderService.model.Orders;
import com.ecommerce.orderService.payload.OrderRequest;
import com.ecommerce.orderService.payload.OrderResponse;
import com.ecommerce.orderService.repository.OrderRepository;
import com.ecommerce.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Orders orders = Orders.builder()
                .userId(orderRequest.getUserId())
                .orderDate(orderRequest.getOrderDate())
//                .productIds(orderRequest.getProductIds())
                .totalAmount(orderRequest.getTotalAmount())
                .status(ChangeStatus.PENDING)
                .build();
        Orders savedOrders = orderRepository.save(orders);

        return OrderResponse.fromOrder(savedOrders);
    }

    @Override
    public Orders updateOrder(Long orderId, OrderRequest updatedOrderRequest) {
        Optional<Orders> existingOrderOptional = orderRepository.findById(orderId);

        if(existingOrderOptional.isPresent()){
            Orders orders = Orders.builder()
                    .orderId(updatedOrderRequest.getOrderId())
                    .orderDate(updatedOrderRequest.getOrderDate())
                    .userId(updatedOrderRequest.getUserId())
                    .status(updatedOrderRequest.getStatus())
//                    .productIds(updatedOrderRequest.getProductIds())
                    .totalAmount(updatedOrderRequest.getTotalAmount())
                    .build();
            return  this.orderRepository.save(orders);
        }
        throw new RuntimeException("Order not found with Id" + orderId);
    }


    @Override
    public Optional<OrderResponse> getOrderById(Long orderId) {
    Optional<Orders> ordersOptional =orderRepository.findById(orderId);
        return ordersOptional.map(OrderResponse::fromOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Orders> ordersList = orderRepository.findAll();

        return ordersList.stream()
                .map(OrderResponse::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);

    }
}
