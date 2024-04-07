package com.ecommerce.productservice.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddProductsRequest {
    private int userId;
    List<Product> products;
}
