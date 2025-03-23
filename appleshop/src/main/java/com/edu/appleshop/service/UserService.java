package com.exe.shojin.service;
import com.exe.shojin.model.User;
public interface UserService {
    void add(User user);
    boolean existsByUsername(String username);
    User findById(String username);
}
