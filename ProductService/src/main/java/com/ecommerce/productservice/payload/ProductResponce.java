package com.ecommerce.productservice.payload;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.model.ProductAvailablity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductResponce {

        private int productId;
        private String productName;
        private String description;
        private double price;
        private int userId;
        private ProductAvailablity productAvailablity;

}


