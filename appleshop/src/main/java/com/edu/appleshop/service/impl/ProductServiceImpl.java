package com.edu.appleshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.appleshop.model.Product;
import com.edu.appleshop.repository.ProductRepository;
import com.edu.appleshop.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
    public List<Product> getProductsByLoai(String category) {
        return productRepository.findByCategory(category);
    }
    

    


}
