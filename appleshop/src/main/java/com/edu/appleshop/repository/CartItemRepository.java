package com.edu.appleshop.repository;

import com.edu.appleshop.model.Cart;
import com.edu.appleshop.model.CartItem;
import com.edu.appleshop.model.Product;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_Username(String username);
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
      @Modifying
    @Transactional  // Quan trọng để đảm bảo delete có transaction
    void deleteByCart_UsernameAndProduct_ProductID(String username, String productID);
    Optional<CartItem> findByCart_UsernameAndProduct_ProductID(String username, String productID);
}
