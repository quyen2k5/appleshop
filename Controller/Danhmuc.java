package com.example.WebBanHang.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanHang.Dao.CategoryDao;
import com.example.WebBanHang.dto.request.CatetoryCheck;
import com.example.WebBanHang.model.Category;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class Danhmuc {
    @Autowired
    CategoryDao categorydao;
   @GetMapping("/Danhmuc")
    public String DanhmucAdmin(Model model){
        model.addAttribute("cate", new CatetoryCheck());
        List<Category> listcate = categorydao.findAll();
        model.addAttribute("listcate", listcate);
        return "form/Danhmuc";
    }
    @PostMapping("/Danhmuc")
    public String CheckDanhmuc(@Valid @ModelAttribute("cate") CatetoryCheck cate, BindingResult result, Model model) {
    if (result.hasErrors()) {
        List<Category> listcate = categorydao.findAll();
        model.addAttribute("listcate", listcate);
        return "form/Danhmuc"; 
    }
    Category category = new Category();
    category.setName(cate.getName());
    categorydao.save(category);
    model.addAttribute("message", "Thêm thành công");
    return "redirect:/Danhmuc"; 
}

}
