package com.ecommerce.userService.payload;

import com.ecommerce.userService.model.Address;
import com.ecommerce.userService.model.ChangeStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequest {
    private int userId;
    private String name;
    private String password;
    private String email;
    private ChangeStatus changeStatus;
    private boolean deleted = false;
    private List<Address> address;
}
