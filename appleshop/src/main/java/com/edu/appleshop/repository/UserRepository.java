package com.edu.appleshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.appleshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  boolean existsByUsername(String username);

}
