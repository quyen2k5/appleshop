package com.example.WebBanHang.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.example.WebBanHang.Dao.OrdersDao;
import com.example.WebBanHang.Dao.OrdersdetailsDao;
import com.example.WebBanHang.SendGmail.SendEmail;
import com.example.WebBanHang.Service.CartService;
import com.example.WebBanHang.model.Order;
import com.example.WebBanHang.model.Ordersdetails;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class Giohang {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    CartService cartService;
    @Autowired
    OrdersdetailsDao ordersdetailsDao;
    @Autowired
    SendEmail sendEmail;
    @GetMapping("/Giohang")
    public String giohangs(Model model, HttpSession session) {
        User user = (User) session.getAttribute("ShowUser");
        if (user == null) {
            model.addAttribute("message","Vui lòng đăng nhập trước");
            return "redirect:/form/Dangnhap";
            
        }
        Order order = ordersDao.findByUserAndStatus(user, 0);
        List<Ordersdetails> cartItems = (order != null) ? order.getOrdersdetails() : List.of();
        model.addAttribute("cartItems", cartItems);
        double tongtien = 0;
    for (Ordersdetails item : cartItems) {
        tongtien += item.getPrice() * item.getQuantity();
    }
    model.addAttribute("cartItems", cartItems);
    model.addAttribute("tong", tongtien);
        return "form/Giohang";
    }

    @PostMapping("/Giohang")
    public String addToCart(@RequestParam("id") Integer productId,@RequestParam(value="price") Double price,@RequestParam(value = "quantity", defaultValue = "1") int quantity,@SessionAttribute(name = "ShowUser", required = false) User user,Model model) {
        if (user == null) {
            return "redirect:/form/Dangnhap";
        }
        Product product = new Product();
        product.setId(productId);
        cartService.addToCard(product, quantity, user ,price);
        model.addAttribute("message", "Đã thêm vào giỏ hàng!");
        return "redirect:/Giohang";
    }
    @PostMapping("/Giohang/DeleteOder")
    public String xoaorder( @RequestParam("id") Integer oderchitietid ,Model model){
        ordersdetailsDao.deleteById(oderchitietid);
        
        return "redirect:/Giohang";
    }
    @PostMapping("/Giohang/Tang")
    public String Tang(@RequestParam("id") Integer oderchitietid ,Model model){
        
        Optional<Ordersdetails> o = ordersdetailsDao.findById(oderchitietid);
        if(o.isPresent()){
            Ordersdetails s = o.get();
            s.setQuantity(s.getQuantity() +1);
            ordersdetailsDao.save(s);
        }
        return "redirect:/Giohang";
    }
    @PostMapping("/Giohang/Giam")
    public String Giam(@RequestParam("id") Integer oderchitietid ,Model model){
        
        Optional<Ordersdetails> o = ordersdetailsDao.findById(oderchitietid);
        if (o.isPresent()) {
            Ordersdetails s = o.get();
            if (s.getQuantity() > 1) { 
                s.setQuantity(s.getQuantity() - 1);
                ordersdetailsDao.save(s);
            } else {
                ordersdetailsDao.delete(s);
            }
        }
        return "redirect:/Giohang";
    }
    @PostMapping("/Giohang/Thanhtoan")
    public String thanhtoan(@RequestParam("province") String province,
            @RequestParam("district") String district, Model model, HttpSession session) {
        User user = (User) session.getAttribute("ShowUser");
        Order order = ordersDao.findByUserAndStatus(user, 0);
        if (order != null) {

            for (Ordersdetails item : order.getOrdersdetails()) {
                item.setIsdelete(true);
                ordersdetailsDao.save(item);
            }
            order.setStatus(1);
            order.setShippingAddress(province + ", " + district);
            ordersDao.save(order);
        }
        return "redirect:/Giohang";
    }
    
}
