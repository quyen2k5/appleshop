package com.example.WebBanHang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WebBanHang.Dao.UserDao;
import com.example.WebBanHang.model.User;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class Quanlinguoidung {
    @Autowired
    UserDao userdao;
    // Trả về trang thêm người dùng với model trống
    @GetMapping("/Quanlinguoidung")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());  // Khởi tạo user trống
        model.addAttribute("userList", userdao.findAll()); // Lấy danh sách người dùng
        return "form/Quanlinguoidung";
    }
   

@GetMapping("/add-user")
public String showAddUserForm(Model model) {
    model.addAttribute("user", new User());
    return "form/AddUser";
}
   // Xử lý thêm người dùngs
   @PostMapping("/add-user")
   public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttrs) {
       if (result.hasErrors()) {
           return "form/Quanlinguoidung"; // Trả về trang với lỗi
       }
       userdao.save(user);
       redirectAttrs.addFlashAttribute("success", "Thêm người dùng thành công!");
       return "redirect:/Quanlinguoidung";
   }

// 🛠 Hiển thị thông tin người dùng khi sửa
@GetMapping("/edit-user/{id}")
public String editUser(@PathVariable("id") Integer id, Model model) { // ⚠ Đổi Long -> Integer
    User user = userdao.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user với ID: " + id));
    model.addAttribute("user", user);
    model.addAttribute("userList", userdao.findAll()); // Hiển thị danh sách bên dưới
    return "form/Quanlinguoidung"; // Trả về trang quản lý người dùng
}

// 🛠 Xử lý cập nhật người dùng
@PostMapping("/update-user/{id}")
public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttrs) { // ⚠ Đổi Long -> Integer
    if (result.hasErrors()) {
        return "form/Quanlinguoidung"; // Trả về form với lỗi
    }
    user.setId(id); // Đảm bảo ID không bị thay đổi
    userdao.save(user); // Cập nhật vào database
    redirectAttrs.addFlashAttribute("success", "Cập nhật thành công!");
    return "redirect:/Quanlinguoidung";
}
}
