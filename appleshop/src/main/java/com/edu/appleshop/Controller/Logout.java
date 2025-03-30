package com.edu.appleshop.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class Logout {
    @RequestMapping("/out")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index/home";
    }
}
