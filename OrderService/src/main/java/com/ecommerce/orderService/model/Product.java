package com.ecommerce.orderService.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {
    private int productId;
    private String productName;
    private String description;
    private double price;
    private int userId;
}
