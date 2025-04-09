package com.example.WebBanHang.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WebBanHang.model.Ordersdetails;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.model.Order;

import java.util.List;
import java.util.Optional;



public interface OrdersdetailsDao extends JpaRepository <Ordersdetails ,Integer> {
        List<Ordersdetails> findByOrder(Order order);
        Optional<Ordersdetails> findByOrderAndProduct( Order order ,  Product product);
        List<Ordersdetails> findByQuantity(int quantity);
        List<Ordersdetails> findByIsdelete(Boolean isdelete);
        
}
