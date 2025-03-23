package com.edu.appleshop.service;
import com.edu.appleshop.model.User;
public interface UserService {
    void add(User user);
    boolean existsByUsername(String username);
    User findById(String username);
}
