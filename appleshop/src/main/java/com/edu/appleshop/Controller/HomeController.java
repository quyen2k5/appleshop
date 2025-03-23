package com.edu.appleshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.appleshop.service.ProductService;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ProductService productService;
    @GetMapping("/home")
    public String showHome(Model model) {
        var product = productService.findAll();
        model.addAttribute("products", product);
        return "home";  
    }

}
