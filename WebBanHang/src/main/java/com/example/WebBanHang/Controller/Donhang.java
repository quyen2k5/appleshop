package com.example.WebBanHang.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.WebBanHang.Dao.OrdersDao;

import com.example.WebBanHang.model.Order;

@Controller
@RequestMapping("/")
public class Donhang {
    @Autowired
    private OrdersDao ordersDao;
    @GetMapping("/Donhang") 
    public String ShowDonhang(Model model){
        List<Order> showOrder = ordersDao.findAll();
        model.addAttribute("showOrder", showOrder);
        return "form/Donhang";
    }
    @GetMapping("/Donhang/details/{id}")
     public String showOrderDetails(@PathVariable Integer id, Model model) {
    Optional<Order> order = ordersDao.findById(id);
    if (order.isPresent()) {
        model.addAttribute("order", order.get());
        return "form/ChitietDonhang"; // Đảm bảo đường dẫn này đúng
    }
    return "redirect:/Donhang"; // Nếu không có order, quay lại danh sách
}
}
