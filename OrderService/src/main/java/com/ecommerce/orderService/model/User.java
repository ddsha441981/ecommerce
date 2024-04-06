package com.ecommerce.orderService.model;

import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class User {
    private int userId;
    private String name;
    private String password;
    private String email;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_user_id",referencedColumnName = "userId")
//    private List<Address> address;
}
