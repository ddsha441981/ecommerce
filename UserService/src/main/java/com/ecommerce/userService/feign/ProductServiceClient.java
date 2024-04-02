package com.ecommerce.userService.feign;


import com.ecommerce.userService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "user-service",url = "${product_service.url}")
public interface ProductServiceClient {

    @GetMapping("/api/v1/products/user/{userId}")
    List<Product> getProductsForUser(@PathVariable int userId);

}


