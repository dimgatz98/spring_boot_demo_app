package com.example.demo.controllers;

import com.example.demo.models.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public Optional<Users> getUser(@PathVariable("userId") Long userId) {
        return this.userService.getUsers(userId);
    }

    @PostMapping
    public void register(@RequestBody Users user) {
        userService.addUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void delete(@PathVariable("userId") Long userId) {
        try{
            userService.deleteUser(userId);
        } catch (Exception e) {
            System.out.println("User does not exist");
        }
    }

    //    Not working
    @PutMapping(path = "{userId}")
    public void update(
            @PathVariable("userId") Long userId,
            @RequestBody(required = false) Users body
            ) {
        try {
            userService.updateUser(
                    userId,
                    body.getUsername(),
                    body.getFullName(),
                    body.getEmail(),
                    body.getBirthDate()
            );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @RestController
    @RequestMapping(path = "api/user/login")
    public class LoginController {
        @PostMapping
        public void login(
                @RequestBody Users body
        ) {
            try {
                userService.login(body.getUsername(), body.getPassword());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
