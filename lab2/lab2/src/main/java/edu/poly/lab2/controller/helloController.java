package edu.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class helloController {
    @RequestMapping("/hello.th")
    public String hello(Model model) {
        model.addAttribute("message", "FPT <b> Polytechnic <b>");
        return "hello";
    }
    
}
