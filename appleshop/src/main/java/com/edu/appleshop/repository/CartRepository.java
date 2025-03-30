package com.edu.appleshop.repository;


import com.edu.appleshop.model.Cart;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
    
}
