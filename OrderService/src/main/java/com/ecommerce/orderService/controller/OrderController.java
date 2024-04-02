package com.ecommerce.orderService.controller;

import com.ecommerce.orderService.model.Orders;
import com.ecommerce.orderService.payload.OrderRequest;
import com.ecommerce.orderService.payload.OrderResponse;
import com.ecommerce.orderService.service.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder (@RequestBody OrderRequest orderRequest){
        OrderResponse createdOrder= orderServiceImpl.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById (@PathVariable Long orderId){
        Optional<OrderResponse> orderById = orderServiceImpl.getOrderById(orderId);
        return orderById
                .map(orderResponse -> ResponseEntity.ok().body(orderResponse))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOredrs(){
        List<OrderResponse> allOrders= orderServiceImpl.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long orderId,
                                              @RequestBody OrderRequest updatedorderRequest){
        Orders updatedOrder=orderServiceImpl.updateOrder(orderId,updatedorderRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long orderId){
        orderServiceImpl.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

