package com.example.WebBanHang.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanHang.Dao.ProductDao;
import com.example.WebBanHang.model.Product;


@Controller
@RequestMapping("/")
public class ChitietSanpham {
    @Autowired
    ProductDao productDao;
    @GetMapping("/ChitietSanpham")
    public String Chitietsanpham(Model model){
        return "form/ChitietSanpham";
    }
    @PostMapping("/ChitietSanpham")
    public String chitetsanphampost(Model model , @RequestParam("id") Integer id){
      Optional<Product> product = productDao.findById(id);
      if(product.isPresent()){
        model.addAttribute("pro", product.get());
      }
      
        return "form/ChitietSanpham";
    }
}
