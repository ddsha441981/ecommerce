package com.ecommerce.orderService.service.serviceImpl;

import com.ecommerce.orderService.model.*;
import com.ecommerce.orderService.payload.OrderRequest;
import com.ecommerce.orderService.payload.OrderResponse;
import com.ecommerce.orderService.repository.OrderRepository;
import com.ecommerce.orderService.service.OrderService;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Value("${user_service_url}")
    private String userUrl;

    @Value("${product_service_url}")
    private String productUrl;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Orders orders = Orders.builder()
                .userId(orderRequest.getUserId())
                .orderDate(orderRequest.getOrderDate())
                .items(orderRequest.getItems())
                .totalAmount(orderRequest.getTotalAmount())
                .status(ChangeStatus.PENDING)
                .build();
        Orders savedOrders = orderRepository.save(orders);

        return OrderResponse.fromOrder(savedOrders);
    }

    @Override
    public Orders updateOrder(int orderId, OrderRequest updatedOrderRequest) {
        Optional<Orders> existingOrderOptional = orderRepository.findById(orderId);

        if(existingOrderOptional.isPresent()){
            Orders orders = Orders.builder()
                    .orderId(updatedOrderRequest.getOrderId())
                    .orderDate(updatedOrderRequest.getOrderDate())
                    .userId(updatedOrderRequest.getUserId())
                    .status(updatedOrderRequest.getStatus())
                    .items(updatedOrderRequest.getItems())
                    .totalAmount(updatedOrderRequest.getTotalAmount())
                    .build();
            return  this.orderRepository.save(orders);
        }
        throw new RuntimeException("Order not found with Id" + orderId);
    }


    @Override
    public Optional<OrderResponse> getOrderById(int orderId) {
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
    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);

    }

    @Override
    public Orders placeOrder(OrderRequest orderRequest) {

        List<Integer> items = new ArrayList<>();
        int userId = orderRequest.getUserId();

        //call user service
        ResponseEntity<User[]> user = restTemplate.getForEntity(userUrl + "/" + userId, User[].class,orderRequest.getUserId());

        User[] users = user.getBody();
        log.info("user response: " + users);
        //call product service
       // ResponseEntity<List<Product[]>> products = restTemplate.getForEntity(productUrl + "/" + userId, Product[].class, orderRequest.getItems());

        ResponseEntity<List<Product[]>> productsResponse = restTemplate.exchange(
                productUrl + "/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product[]>>() {}
        );

        List<Product[]> products = productsResponse.getBody();

//        Product[] product = products.getBody();
        log.info("Product response: " + productsResponse);

        Orders order = Orders.builder()
                .userId(orderRequest.getUserId())
                .orderDate(orderRequest.getOrderDate())
                .items(orderRequest.getItems())
                .totalAmount(orderRequest.getTotalAmount())
                .status(ChangeStatus.PENDING)
                .build();
        this.orderRepository.save(order);
        return order;

    }

//    private  OrderResponse fromOrder(Orders orders) {
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
