package com.example.WebBanHang.Service;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanHang.Dao.OrdersDao;
import com.example.WebBanHang.Dao.OrdersdetailsDao;
import com.example.WebBanHang.Dao.ProductDao;
import com.example.WebBanHang.model.Order;
import com.example.WebBanHang.model.Ordersdetails;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.model.User;


@Service
public class CartService {
    @Autowired
    ProductDao productDao;
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    OrdersdetailsDao odao;
    public  void addToCard(Product product ,int quantity ,User user ,Double price){
        Order orders = ordersDao.findByUserAndStatus(user,0);
        if (orders == null) {
            orders = new Order();
            orders.setUser(user);
            orders.setStatus(0); 
            orders.setCreatedDate(LocalDateTime.now());
            orders.setActivated(true);
            ordersDao.save(orders);
        }
        Optional<Ordersdetails> checkcart = odao.findByOrderAndProduct(orders, product);
        if(checkcart.isPresent()){
            Ordersdetails orderDetail = checkcart.get();
            orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
            odao.save(orderDetail);
        }else{
            Ordersdetails newOrderDetail = new Ordersdetails();
            newOrderDetail.setOrder(orders);
            newOrderDetail.setPrice(price);
            newOrderDetail.setQuantity(quantity);
            newOrderDetail.setProduct(product);
            newOrderDetail.setIsdelete(false);
            odao.save(newOrderDetail);
        }
      
    }
    
}
