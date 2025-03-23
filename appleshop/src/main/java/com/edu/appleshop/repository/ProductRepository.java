package com.edu.appleshop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.edu.appleshop.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    


}
