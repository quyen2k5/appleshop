package com.example.WebBanHang.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanHang.Dao.UserDao;
import com.example.WebBanHang.SendGmail.SendEmail;

@Controller
@RequestMapping("/")
public class Quenmatkhau {
   @Autowired
   private SendEmail sendEmail;
    UserDao userDao;
    @GetMapping("/Quenmatkhau")
    public String quenmatkhau(Model model){
        return "form/Quenmatkhau";
    }
    @PostMapping("/Quenmatkhau")
    public String checkquenmatkhau(Model model ,@RequestParam("email") String email){
        String tieude = "Thay đổi mật khẩu";
        String noidung = "bạn có một yêu cầu sửa mật khẩu!!!";
        sendEmail.sendEmail(email, tieude, noidung);
        model.addAttribute("message", "Hãy kiểm tra hộp thư của bạn");
        return "redirect:/Quenmatkhau";
          
        }
    }

