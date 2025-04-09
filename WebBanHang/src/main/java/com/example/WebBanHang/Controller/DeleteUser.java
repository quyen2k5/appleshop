package com.example.WebBanHang.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanHang.Dao.UserDao;
import com.example.WebBanHang.model.User;

@Controller
@RequestMapping("/")
public class DeleteUser {
    @Autowired
    UserDao userdao; 
    
    @PostMapping("/DeleteUser")
    public String deleteUser(Model model ,@RequestParam("id" ) Integer id){
        Optional<User> userRole = userdao.findById(id);
        if(userRole.isPresent()){
            User users = userRole.get();
            if(users.isRole()){
                model.addAttribute("message", "Bạn không thể xóa tài khoản thuộc Admin!");
                List<User> user = userdao.findAll();
          model.addAttribute("user", user);
             return "form/Quanlinguoidung";
            }
        }
        userdao.deleteById(id);
        List<User> user = userdao.findAll();
        model.addAttribute("user", user);
        return "redirect:/Quanlinguoidung";
    }
}
