package com.example.WebBanHang.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.Category;

public interface CategoryDao extends JpaRepository <Category ,Integer> {
    
}
