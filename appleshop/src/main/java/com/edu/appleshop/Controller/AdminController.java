package com.edu.appleshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.edu.appleshop.model.Product;
import com.edu.appleshop.model.User;
import com.edu.appleshop.service.ProductService;
import com.edu.appleshop.service.UserService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String showHome(Model model, HttpSession session) {
        User logUser = (User) session.getAttribute("logUser");

        if (logUser == null) {
            return "redirect:/login/log"; // Nếu chưa đăng nhập, chuyển hướng về trang login
        }

        var products = productService.findAll(); // Lấy danh sách sản phẩm
        var users = userService.findAll();
        model.addAttribute("user", logUser);
        model.addAttribute("products", products);
        model.addAttribute("users", users);
        return "admin"; // Trả về trang admin.html
    }

    @GetMapping("/add")
    public String addSanPham(Model model) {
        Product products = new Product();
        products.setStatus("Còn Hàng");
        model.addAttribute("products", products);
        return "addproduct";
    }

    @PostMapping("/add")
    public String addSanPham(Model model, @ModelAttribute("products") Product product) {
        productService.add(product);
        model.addAttribute("message", "them thanh cong");
        return "admin";
    }

    @PostMapping("/saveProduct")
    public String saveOrUpdate(RedirectAttributesModelMap model, Product product) {
        productService.add(product);
        model.addFlashAttribute("message", "Cập nhật thành công!");
        return "redirect:/admin/home";
    }

    @GetMapping("/edit/{productID}")
    public String edit(Model model, @PathVariable("productID") String productID) {
        Product products = productService.findByID(productID);
        model.addAttribute("products", products);
        return "editproduct";
    }

    @GetMapping("/delete/{productID}")
    public String delete(RedirectAttributesModelMap model, @PathVariable("productID") String productID) {
        productService.delete(productID);
        model.addAttribute("message", "Xóa thành công");
        return "redirect:/admin/home";
    }

    @GetMapping("/adduser")
    public String addUser(Model model){
        User users = new User();
        users.setRole("user");
        model.addAttribute("users", users);
        return "adduser";
    }
    @PostMapping("/adduser")
    public String addUser(Model model, @ModelAttribute("users") User user){
        userService.add(user);
        model.addAttribute("null", "them tahnh cong");
        return "admin";
    }
    @PostMapping("/saveUser")
    public String saveveOrUpdateUser(RedirectAttributesModelMap model, User user){
        userService.add(user);
        model.addFlashAttribute("message", "cap nhat thanh cong");
        return "redirect:/admin/home";
    }
    @GetMapping("/edituser/{username}")
    public String editUser(Model model, @PathVariable("username") String username){
        User users = userService.findById(username);
        model.addAttribute("users", users);
        return "edituser";
    }
    @GetMapping("/deleteuser/{username}")
    public String deleteUser(RedirectAttributesModelMap model, @PathVariable("username") String username){
        userService.delete(username);
        model.addAttribute("message", "xoa thanh cong");
        return "redirect:/admin/home";
    }
}
