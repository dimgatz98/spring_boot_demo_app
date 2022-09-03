package com.example.demo.controllers;

import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;
import com.example.demo.services.UserService;
import com.example.demo.utils.JwtResponse;
import com.example.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/user")
@Slf4j
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Users> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public Optional<Users> getUser(@PathVariable("userId") Long userId) {
        return this.userService.getUser(userId);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Users user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userService.addUser(user);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/user").toUriString()
            );
            return ResponseEntity.created(uri).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Long userId) {
        try{
            this.userService.deleteUser(userId);
            return ResponseEntity.ok().body("User " + userId + " deleted successfully");
        } catch (Exception e) {
            log.info("User does not exist");
            return ResponseEntity.badRequest().body("User " + userId + " does not exist");
        }
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<?> update(
            @PathVariable("userId") Long userId,
            @RequestBody(required = false) Users body
            ) {
        try {
            this.userService.updateUser(
                    body.getUsername(),
                    body.getFullName(),
                    body.getEmail(),
                    body.getBirthDate(),
                    passwordEncoder.encode(body.getPassword())
            );
            return ResponseEntity.ok().body("User updated successfully");
        } catch (Exception e) {
            log.info(e.toString());
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Users loginRequest
    ) {
        try {
            log.info("pass: " + loginRequest.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.error(e.toString());
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        final Optional<Users> user = this.userRepository.findByUsername(
                loginRequest.getUsername()
        );
        final String jwtToken = this.jwtUtil.generateToken(user.get());
        return ResponseEntity.ok().body(new JwtResponse(jwtToken));
    }
}
