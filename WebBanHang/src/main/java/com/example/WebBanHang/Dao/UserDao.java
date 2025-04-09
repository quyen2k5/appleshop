package com.example.WebBanHang.Dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.User;

public interface  UserDao extends JpaRepository< User ,Integer> {
    Optional<User> findByEmailAndPassword(String email ,String password  );
    Optional<User> findByRole(Boolean role);
    Optional<User> findByEmail(String email); 
    Optional findById(Long id);
}
