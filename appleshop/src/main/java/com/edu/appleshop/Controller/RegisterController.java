package com.exe.shojin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exe.shojin.model.User;
import com.exe.shojin.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/reg")
    public String addForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/reg")
    public String add(Model model, @ModelAttribute("user") User user) {
       if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "username da ton tai");
            return "register";
       }
       user.setRole("user");
         userService.add(user); 
       
        return "redirect:/login/log";
    }
}
