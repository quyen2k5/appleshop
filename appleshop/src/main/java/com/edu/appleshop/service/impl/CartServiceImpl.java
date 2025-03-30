package com.edu.appleshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.appleshop.model.Cart;
import com.edu.appleshop.model.CartItem;
import com.edu.appleshop.model.Product;
import com.edu.appleshop.repository.CartItemRepository;
import com.edu.appleshop.repository.CartRepository;
import com.edu.appleshop.repository.ProductRepository;
import com.edu.appleshop.service.CartService;

import jakarta.transaction.Transactional;
@Service
public class CartServiceImpl implements CartService{
     @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
@Transactional
public String addToCart(String username, String productID) {
    Product product = productRepository.findById(productID).orElse(null);
    if (product == null) {
        return "Sản phẩm không tồn tại!";
    }

    if (product.getQuantity() == null || product.getQuantity() <= 0) {
        return "Sản phẩm đã hết hàng!";
    }

    Cart cart = cartRepository.findById(username).orElseGet(() -> {
        Cart newCart = new Cart();
        newCart.setUsername(username);
        return cartRepository.save(newCart);
    });

    CartItem cartItem = cartItemRepository.findByCart_UsernameAndProduct_ProductID(username, productID)
            .orElseGet(() -> {
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(0);
                return newItem;
            });

    int newQuantity = cartItem.getQuantity() + 1;

    // Kiểm tra số lượng tồn kho
    if (newQuantity > product.getQuantity()) {
        return "Không thể thêm sản phẩm vào giỏ hàng, số lượng trong kho không đủ!";
    }

    cartItem.setQuantity(newQuantity);
    cartItemRepository.save(cartItem);

    return "Thêm sản phẩm vào giỏ hàng thành công!";
}


    public List<CartItem> getCartItems(String username) {
        return cartItemRepository.findByCart_Username(username);
    }

    @Override
    @Transactional  // Đảm bảo transaction khi xóa
    public void removeFromCart(String username, String productID) {
        Cart cart = cartRepository.findById(username).orElse(null);
        if (cart == null) return;

        cartItemRepository.deleteByCart_UsernameAndProduct_ProductID(username, productID);
    }
}
