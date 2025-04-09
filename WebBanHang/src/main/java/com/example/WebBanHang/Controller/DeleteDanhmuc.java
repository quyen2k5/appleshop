package com.example.WebBanHang.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanHang.Dao.CategoryDao;
import com.example.WebBanHang.model.Category;

@Controller
@RequestMapping("/")
public class DeleteDanhmuc {
    @Autowired
    CategoryDao categoryDao;
    @PostMapping("/DeleteDanhmuc")
    public String deleteDanhmuc(Model model,@RequestParam("id") Integer id){
        categoryDao.deleteById(id);
         List<Category> listcate = categoryDao.findAll();
        model.addAttribute("listcate", listcate);
        return "redirect:/Danhmuc";
    }
}
