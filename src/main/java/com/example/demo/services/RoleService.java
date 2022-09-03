package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.RoleRepository;
import com.example.demo.models.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService (UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public void saveRole(Role role) {
        this.roleRepository.save(role);
    }

    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }
}
