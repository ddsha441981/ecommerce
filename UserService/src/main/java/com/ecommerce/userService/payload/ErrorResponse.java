package com.ecommerce.userService.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ErrorResponse {

    private Boolean success;
    private String errorMessage;
    private int  errorCode;

}
