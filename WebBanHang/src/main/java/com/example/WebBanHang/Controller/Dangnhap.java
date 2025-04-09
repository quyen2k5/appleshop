package com.example.WebBanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanHang.Dao.UserDao;
import com.example.WebBanHang.dto.request.UserLoginRequest;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/form")
public class Dangnhap {
  @Autowired
  private UserDao userdao;

  @GetMapping("/Dangnhap")
  public String dangky(Model model) {
    model.addAttribute("user", new UserLoginRequest());
    return "form/Dangnhap";
  }

  @PostMapping("/Dangnhap")
  public String checkDangnhap(@Valid @ModelAttribute("user") UserLoginRequest user, BindingResult result, Model model,
      HttpSession session) {
    if (result.hasErrors()) {
      return "form/Dangnhap";
    }
    if (userdao.findByEmail(user.getEmail()).isEmpty()) {
      model.addAttribute("message", "Email không tồn tại. Vui lòng kiểm tra lại.");
      return "form/Dangnhap";
    }

    var sessionUser = userdao.findByEmailAndPassword(user.getEmail(), user.getPassword());
    if (sessionUser.isPresent()) {
      session.setAttribute("ShowUser", sessionUser.get());
      model.addAttribute("message", "Đăng nhập thành công");
      return "redirect:/thienduonganmac";
    } else {
      model.addAttribute("message", "Mật khẩu không đúng. Vui lòng thử lại.");
      return "form/Dangnhap";
    }
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate(); // Xóa toàn bộ session
    return "redirect:/form/Dangnhap";
  }
}
