package com.example.WebBanHang.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.WebBanHang.Dao.CategoryDao;
import com.example.WebBanHang.Dao.ProductDao;
import com.example.WebBanHang.dto.request.ProductRequest;
import com.example.WebBanHang.model.Category;
import com.example.WebBanHang.model.Product;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class Quanlisanpham {
    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;
    @GetMapping("/Quanlisanpham")
    public String QuanlisanphamAdmin(Model model){
        model.addAttribute("pro", new ProductRequest());

        List<Product> sanpham = productDao.findByIsdelete(false);
        model.addAttribute("sanpham", sanpham);

        List<Category> cate = categoryDao.findAll();
        model.addAttribute("cate", cate);
        
        
        return "form/Quanlisanpham";
    }
    @PostMapping("/Quanlisanpham")
    public String QuanlisanphamCheck(Model model ,@Valid @ModelAttribute("pro") ProductRequest productRequest ,BindingResult result ){
        if(result.hasErrors()){
            List<Product> sanpham = productDao.findAll();
        model.addAttribute("sanpham", sanpham);
        List<Category> cate = categoryDao.findAll();
        model.addAttribute("cate", cate);
        return "form/Quanlisanpham";
        }
        try {
            MultipartFile imgFile = productRequest.getImg();
            String fileName = imgFile.getOriginalFilename();
            String uploadDir = "src/main/resources/static/thotos/";
            Path filePath = Paths.get(uploadDir + fileName);
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.write(filePath, imgFile.getBytes());
            Category category = categoryDao.findById(productRequest.getCategoryId()).orElse(null);
            Product product = new Product();
            product.setCategory(category);
            product.setTitle(productRequest.getTitle());
            product.setPrice(productRequest.getPrice());
            product.setImg("/thotos/"+fileName);
            product.setDescription(productRequest.getDescription());
            product.setIsdelete(false);
            productDao.save(product); 
            model.addAttribute("message", "Thêm sản phẩm thành công!");
            List<Product> sanpham = productDao.findByIsdelete(false);
            model.addAttribute("sanpham", sanpham);
            List<Category> cate = categoryDao.findAll();
            model.addAttribute("cate", cate);
            return "redirect:/Quanlisanpham";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi tải lên ảnh hoặc lưu sản phẩm!");
        }
        
        return "form/Quanlisanpham";
    }
  // updata san pham
  @GetMapping("/edit/{id}")
public String editProduct(@PathVariable("id") Long id, Model model) {
    Product product = (Product) productDao.findById(id).orElse(null);
    if (product == null) {
        return "redirect:/Quanlisanpham";
    }
    ProductRequest productRequest = new ProductRequest();
    productRequest.setId(product.getId());
    productRequest.setTitle(product.getTitle());
    productRequest.setPrice(product.getPrice());
    productRequest.setDescription(product.getDescription());
    productRequest.setCategoryId(product.getCategory().getId());

    model.addAttribute("pro", productRequest);
    model.addAttribute("sanpham", productDao.findByIsdelete(false));
    model.addAttribute("cate", categoryDao.findAll());
    
    return "form/EditProduct";
}

@PostMapping("/edit")
public String updateProduct(@Valid @ModelAttribute("pro") ProductRequest productRequest, BindingResult result, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("cate", categoryDao.findAll());
        return "form/EditProduct";
    }
    Product product = productDao.findById(productRequest.getId()).orElse(null);
    if (product == null) {
        return "redirect:/Quanlisanpham";
    }

    try {
        MultipartFile imgFile = productRequest.getImg();
        if (!imgFile.isEmpty()) {
            String fileName = imgFile.getOriginalFilename();
            String uploadDir = "src/main/resources/static/thotos/";
            Path filePath = Paths.get(uploadDir + fileName);
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            Files.write(filePath, imgFile.getBytes());
            product.setImg("/thotos/" + fileName);
        }

        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setCategory(categoryDao.findById(productRequest.getCategoryId()).orElse(null));
        productDao.save(product);

        return "redirect:/Quanlisanpham";
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("message", "Lỗi khi cập nhật sản phẩm!");
        return "form/EditProduct";
    }
}

 
}
