package com.exe.shojin.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exe.shojin.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    


}
