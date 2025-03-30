package com.exe.shojin.service;

import java.util.List;

import com.exe.shojin.model.CartItem;

public interface CartService {
    String addToCart(String username, String productID);
    List<CartItem> getCartItems(String username);
    void removeFromCart(String username, String productID);
}
