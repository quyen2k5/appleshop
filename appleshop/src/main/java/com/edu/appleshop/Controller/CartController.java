package com.edu.appleshop.Controller;

import com.edu.appleshop.model.*;
import com.edu.appleshop.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    // @Autowired
    // private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/add/{productID}")
    public String addToCart(@PathVariable("productID") String productID, HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");
        if (logUser == null) {
            return "redirect:/login";
        }

        cartService.addToCart(logUser.getUsername(), productID);
        return "redirect:/index/home2";
    }

    @GetMapping("")
    public String viewCart(Model model, HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");
        if (logUser == null) {
            return "redirect:/login";
        }

        List<CartItem> cartItems = cartService.getCartItems(logUser.getUsername());
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @GetMapping("/remove/{productID}")
    public String removeFromCart(@PathVariable("productID") String productID, HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");
        if (logUser == null) {
            return "redirect:/login";
        }

        cartService.removeFromCart(logUser.getUsername(), productID);
        return "redirect:/cart";
    }
}
