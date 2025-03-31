package com.example.WebBanHang.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Hethongquanli {
    @GetMapping("/Hethongquanli")
    public String QuanlihethongAdmin(Model model){
        return "form/Hethongquanli";
    }
}
