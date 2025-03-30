package com.edu.appleshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;



import com.edu.appleshop.model.Product;
import com.edu.appleshop.model.User;
import com.edu.appleshop.service.ProductService;

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
        List<Product> sanPhamAirPod = productService.getProductsByLoai("airpods");
        List<Product> sanPhamAppleWatch = productService.getProductsByLoai("applewatch");
        var product = productService.findAll();
        model.addAttribute("products", product);
        model.addAttribute("sanPhamIphone", sanPhamIphone);
        model.addAttribute("sanPhamMacBook", sanPhamMacBook);
        model.addAttribute("sanPhamAirPod", sanPhamAirPod);
        model.addAttribute("sanPhamAppleWatch", sanPhamAppleWatch);

        return "home";
    }

    @GetMapping("/home2")
    public String showHome2(Model model, HttpSession session) {
        List<Product> sanPhamIphone = productService.getProductsByLoai("iphone");
        List<Product> sanPhamMacBook = productService.getProductsByLoai("macbook");
        List<Product> sanPhamAirPod = productService.getProductsByLoai("airpods");
        List<Product> sanPhamAppleWatch = productService.getProductsByLoai("applewatch");
        var product = productService.findAll();
        User logUser = (User) session.getAttribute("logUser");
        if (logUser != null) {
            model.addAttribute("user", logUser);
        }
        model.addAttribute("products", product);
        model.addAttribute("sanPhamIphone", sanPhamIphone);
        model.addAttribute("sanPhamMacBook", sanPhamMacBook);
        model.addAttribute("sanPhamAirPod", sanPhamAirPod);
        model.addAttribute("sanPhamAppleWatch", sanPhamAppleWatch);
        
        return "home2";
    }

    @GetMapping("/user")
    public String productHome(Model model, HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");
        if (logUser != null) {
            model.addAttribute("user", logUser);
            return "redirect:/index/home2";

        }
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String adminHome(Model model, HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");
        if (logUser != null) {
            model.addAttribute("user", logUser);
            return "redirect:/admin/home";
        }
        return "redirect:/login/log";
    }

   




}


