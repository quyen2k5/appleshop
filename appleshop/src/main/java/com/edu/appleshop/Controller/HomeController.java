package com.edu.appleshop.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exe.shojin.model.Product;
import com.exe.shojin.model.User;
import com.exe.shojin.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/index")
public class HomeController {

    @Autowired
    private ProductService productService;
    @GetMapping("/home")
    public String showHome(Model model) {
        List<Product> sanPhamIphone = productService.getProductsByLoai("iphone");
        List<Product> sanPhamMacBook = productService.getProductsByLoai("macbook");
        var product = productService.findAll();
        model.addAttribute("products", product);
        model.addAttribute("sanPhamIphone", sanPhamIphone);
        model.addAttribute("sanPhamMacBook", sanPhamMacBook);

        
        return "home";  
    } 
    @GetMapping("/user")
    public String productHome( Model model,HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");
        if (logUser != null) {
            model.addAttribute("user", logUser);
            return "redirect:/index/home"; 
        }
        return  "redirect:/login"; 
    }
     @GetMapping("/admin")
    public String adminHome(Model model, HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");
        if (logUser != null) {
            model.addAttribute("user", logUser);
            return "admin";
        }
        return "redirect:/login";
    }
   

}

