package com.exe.shojin.repository;


import com.exe.shojin.model.Cart;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
    
}
