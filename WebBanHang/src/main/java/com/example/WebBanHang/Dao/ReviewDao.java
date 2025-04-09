package com.example.WebBanHang.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanHang.model.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(Integer productId);
}