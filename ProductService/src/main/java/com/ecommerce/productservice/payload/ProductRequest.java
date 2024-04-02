package com.ecommerce.productservice.payload;

import com.ecommerce.productservice.model.ProductAvailablity;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductRequest {

        private int productId;
        private String productName;
        private String description;
        private double price;
        private int userId;
        private ProductAvailablity productAvailablity;

}
