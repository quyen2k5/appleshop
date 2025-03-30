package com.edu.appleshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.appleshop.model.User;
import com.edu.appleshop.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/log")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }
    @PostMapping("/log")
    public String login(User user, Model model, HttpSession session) {
        User exUser = userService.findById(user.getUsername());
        if (exUser != null && exUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("logUser", exUser);
            if ("admin".equals(exUser.getRole())) {
                return "redirect:/index/admin";
            } else if("user".equals(exUser.getRole())) {
                return "redirect:/index/user";
            }
        }
        model.addAttribute("error", "false username hoặc password");
        return "login";
    }
}
