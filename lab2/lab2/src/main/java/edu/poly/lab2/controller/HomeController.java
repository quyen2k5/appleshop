package edu.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home/index")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf");
        return "home/index";
    }
}
