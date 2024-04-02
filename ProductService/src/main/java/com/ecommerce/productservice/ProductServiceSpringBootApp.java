package com.ecommerce.productservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
 class ProductServiceSpringBootApp {
    public static void main( String[] args )
    {
        log.info("Product Service Started");
        SpringApplication.run(ProductServiceSpringBootApp.class);
    }
}
