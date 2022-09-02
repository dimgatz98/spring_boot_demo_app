package com.example.demo.services;

import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUsers(Long userId) {
        return userRepository.findById(userId);
    }

    public void addUser(Users user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) throws Exception {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new Exception("User does not exist");
        } else {
            userRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updateUser(
            Long userId,
            String username,
            String fullName,
            String email,
            LocalDate birthDate
    ) throws Exception {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new Exception("User does not exist");
        }

        Users user = userRepository.getReferenceById(userId);

        if (email != null)
            user.setEmail(email);
        if (username != null)
            user.setUsername(username);
        if (fullName != null)
            user.setFullName(fullName);
        if (birthDate != null)
            user.setBirthDate(birthDate);
    }

    // Token needs to be returned when login is successful, exception otherwise
    public void login(String username, String password) throws Exception {
        Optional<Users> user = userRepository.findByUsername(username);
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        if (user.isEmpty()) {
            throw new Exception("User doesn't exist");
        } else if (Objects.equals(user.get().getPassword(), encodedPassword)) {
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Invalid credentials");
        }
    }

}
