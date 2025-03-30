package com.edu.appleshop.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edu.appleshop.model.Product;

public interface ProductService {

    Iterable<Product> findAll();
    void add(Product product);
    List<Product> getProductsByLoai(String category);
     Product findByID(String productID);
     void delete(String productID);
     Page<Product> searchName(String searchNameProduct, Pageable pageable);
}
