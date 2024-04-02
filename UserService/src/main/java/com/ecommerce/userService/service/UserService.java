package com.ecommerce.userService.service;

import com.ecommerce.userService.model.ChangeStatus;
import com.ecommerce.userService.model.Product;
import com.ecommerce.userService.model.User;
import com.ecommerce.userService.payload.UserRequest;
import com.ecommerce.userService.payload.UserResponse;
import com.ecommerce.userService.payload.UserResponsePagination;

import java.util.List;

public interface UserService {

    //add user
    User addUser(UserRequest userRequest);

    //update user
    User updateUser(int userId, UserRequest userRequest);

    //get all user
    List<UserResponse> listUsers();

    //get by id
    UserResponse getById(int userId);

    //delete
    void deleteById(int userId);

    //change status
    User changeStatus(int userId, String changeStatus);
    void isDeleted(int userId);

    List<UserResponsePagination> pagination(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<Product> getProductsForUser(int userId);

    List<Product> getAllProducts();
}
