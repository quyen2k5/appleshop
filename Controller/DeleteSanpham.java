package com.example.WebBanHang.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanHang.Dao.CategoryDao;
import com.example.WebBanHang.Dao.ProductDao;
import com.example.WebBanHang.model.Category;
import com.example.WebBanHang.model.Product;
@Controller
@RequestMapping("/")
public class DeleteSanpham {
    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;
       @PostMapping("/DeleteSanpham")
    public String deleteSanpham(@RequestParam("id") Integer id ,Model model){
        Product p = productDao.findById(id).orElse(null);
        if(p != null){
            p.setIsdelete(true);
            productDao.save(p);
        }
        List<Product> sanpham = productDao.findByIsdelete(false);
        model.addAttribute("sanpham", sanpham);
        List<Category> cate = categoryDao.findAll();
        model.addAttribute("cate", cate);
        return "redirect:/Quanlisanpham";
    }
}
