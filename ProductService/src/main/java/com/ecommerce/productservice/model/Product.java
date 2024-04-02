package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name ="product")
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private String description;
    private double price;
    private int userId;
    @Enumerated(EnumType.STRING)
    private ProductAvailablity productAvailablity;

}
