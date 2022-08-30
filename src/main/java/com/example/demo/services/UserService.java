package com.example.demo.services;

import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}
