package com.edu.appleshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void add(Product product) {
        productRepository.save(product);
    }

    public void delete(String productID) {
        productRepository.deleteById(productID);
    }

    public List<Product> getProductsByLoai(String category) {
        return productRepository.findByCategory(category);
    }

    public Product findByID(String productID){
        Optional<Product> product = productRepository.findById(productID);
        return product.orElse(null);
    }   

    public Page<Product> searchName(String searchNameProduct, Pageable pageable) {
        return productRepository.search(searchNameProduct, pageable);
    }
    


}

