package com.example.WebBanHang.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebBanHang.model.Product;

public interface ProductDao extends JpaRepository<Product ,Integer>  {
    List<Product> findByCategoryId(Integer categoryId);
    List<Product> findByIsdelete(Boolean isdelete);
    Optional findById(Long id);
    List<Product> findByTitleContainingIgnoreCaseAndIsdelete(String title, boolean isdelete);
    List<Product> findByCategoryIdAndIsdelete(Integer categoryId, boolean isdelete);

}
