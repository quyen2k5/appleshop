package com.edu.appleshop.service;

import java.util.List;

import com.edu.appleshop.model.CartItem;

public interface CartService {
    String addToCart(String username, String productID);
    List<CartItem> getCartItems(String username);
    void removeFromCart(String username, String productID);
}
