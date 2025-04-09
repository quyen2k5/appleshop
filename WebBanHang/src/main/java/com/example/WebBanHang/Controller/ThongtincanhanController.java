package com.example.WebBanHang.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.WebBanHang.Dao.UserDao;
import com.example.WebBanHang.model.User;

@Controller
@RequestMapping("/")
public class ThongtincanhanController {

    @Autowired
    private UserDao userDao;

    // Hiển thị trang thông tin cá nhân
  // Hiển thị trang thông tin cá nhân
  @GetMapping("/Thongtincanhan")
  public String showUserProfile(@RequestParam("id") Integer id, Model model) {
      Optional<User> userOptional = userDao.findById(id);
      if (userOptional.isEmpty()) {
          throw new IllegalArgumentException("Không tìm thấy user với ID: " + id);
      }
      
      model.addAttribute("user", userOptional.get());
      return "form/Thongtincanhan";
  }

  // Cập nhật thông tin cá nhân
  @PostMapping("/Thongtincanhan")
  public String updateUserProfile(
          @ModelAttribute User user,
          @RequestParam(value = "photo", required = false) MultipartFile file) {
      try {
          // Nếu có file ảnh mới, lưu ảnh vào thư mục uploads
          if (file != null && !file.isEmpty()) {
              String uploadDir = "uploads/";
              File uploadFolder = new File(uploadDir);
              if (!uploadFolder.exists()) {
                  uploadFolder.mkdirs();
              }
              
              String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
              File destinationFile = new File(uploadDir + fileName);
              file.transferTo(destinationFile);

              // Cập nhật đường dẫn ảnh mới vào user
              user.setPhoto("/" + uploadDir + fileName);
          }

          // Lưu thông tin cập nhật vào database
          userDao.save(user);
          return "redirect:/Thongtincanhan?id=" + user.getId(); // Quay lại trang cá nhân sau khi cập nhật
      } catch (IOException e) {
          e.printStackTrace();
          return "redirect:/Thongtincanhan?error=upload_failed";
      }
  }
}