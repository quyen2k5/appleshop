package com.example.WebBanHang.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WebBanHang.model.Order;
import com.example.WebBanHang.model.User;

public interface OrdersDao extends JpaRepository <Order ,Integer> {
    Order findByUserAndStatus(User user, int status);
}
