package com.edu.appleshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


import com.edu.appleshop.model.User;
import com.edu.appleshop.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserService userService;
    @GetMapping("/edit/{username}")
    public String editProfile(@PathVariable("username") String username, Model model){

        User user = userService.findById(username);
        user.setRole("user");
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/save")
    public String saveOrUpdate(RedirectAttributesModelMap model, User user) {
        userService.add(user);
        model.addFlashAttribute("message", "Cập nhật thành công!");
        return "profile";
    }
}
