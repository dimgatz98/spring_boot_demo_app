package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.RoleRepository;
import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service @Slf4j @Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService (UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public void addUser(Users user) {
        userRepository.save(user);
        addRoleToUser(user.getUsername(), "ROLE_USER");
    }

    public void deleteUser(Long userId) throws Exception {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new Exception("User does not exist");
        } else {
            userRepository.deleteById(userId);
        }
    }

    public void updateUser(
            String username,
            String fullName,
            String email,
            LocalDate birthDate,
            String password
    ) throws Exception {
        Users user = userRepository.findByUsername(username).get();

        if (email != null)
            user.setEmail(email);
        if (username != null)
            user.setUsername(username);
        if (fullName != null)
            user.setFullName(fullName);
        if (birthDate != null)
            user.setBirthDate(birthDate);
        if (password != null)
            user.setPassword(password);
    }

    public void addRoleToUser(String username, String roleName) {
        Optional<Users> user = userRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);
        user.get().getRoles().add(role.get());
    }
}
