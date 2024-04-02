package com.ecommerce.orderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@Slf4j
public class OrderServiceSpringBootApp
{
    public static void main( String[] args )
    {
        log.info("Order Service started successfully");
        SpringApplication.run(OrderServiceSpringBootApp.class);
    }
}
