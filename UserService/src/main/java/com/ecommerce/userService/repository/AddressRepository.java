package com.ecommerce.userService.repository;

import com.ecommerce.userService.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
