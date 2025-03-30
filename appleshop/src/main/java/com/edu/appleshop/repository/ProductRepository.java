package com.edu.appleshop.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.appleshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCategory(String category);

    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE ?1")
    Page<Product> search(String searchNameProduct, Pageable pageable);

}
