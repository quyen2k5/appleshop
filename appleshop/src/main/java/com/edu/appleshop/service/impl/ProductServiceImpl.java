package com.exe.shojin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.shojin.model.Product;
import com.exe.shojin.repository.ProductRepository;
import com.exe.shojin.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    


}
