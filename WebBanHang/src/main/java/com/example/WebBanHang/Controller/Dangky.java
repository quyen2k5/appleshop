package com.example.WebBanHang.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.WebBanHang.dto.request.UserDangky;
import com.example.WebBanHang.Dao.UserDao;
import com.example.WebBanHang.SendGmail.SendEmail;
import com.example.WebBanHang.model.User;

import jakarta.validation.Valid;







@Controller
@RequestMapping("/form")
public class Dangky {
    @Autowired
    private UserDao userdao;
    @Autowired
    SendEmail sendEmail;
    @GetMapping("/Dangky")
    public String dangky(Model model ){
      model.addAttribute("user", new UserDangky());
        return "form/Dangky";
    }
   @PostMapping("/Dangky")
    public String checkDangky(@Valid @ModelAttribute("user") UserDangky user, BindingResult result,   Model model) {
    if (result.hasErrors()) {
        return "form/Dangky";   
    }
    if (userdao.findByEmail(user.getEmail()).isPresent()) {
        model.addAttribute("message", "Email đã tồn tại!");
        return "form/Dangky";
    }
    
    try {
    User users = new User();
    users.setFullname(user.getFullname());
    users.setEmail(user.getEmail());
    users.setPassword(user.getPassword());
    users.setRole(false);
    users.setActivated(true);
    userdao.save(users);
    model.addAttribute("message", "Đăng ký thành công!");
    String tieude = "Đăng ký tài khoản";
    String noidung = "Chào mừng bạn đến với Website AppSHOP chúc bạn mua sắm vui vẻ";
    sendEmail.sendEmail(user.getEmail(), tieude, noidung);
    return  "form/Dangnhap";
} catch (Exception ex) {
    model.addAttribute("message", "Đăng ký thất bại: " + ex.getMessage());
}
  return "form/Dangky";
}
}
