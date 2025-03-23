package com.exe.shojin.service.impl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.shojin.model.User;
import com.exe.shojin.repository.UserRepository;
import com.exe.shojin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
   private UserRepository userRepository;

  
    public void add(User user) {
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findById(String username) {
       Optional<User> user = userRepository.findById(username);
       return user.orElse(null);
    }

}
