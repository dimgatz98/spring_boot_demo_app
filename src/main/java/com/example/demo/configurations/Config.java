package com.example.demo.configurations;

import com.example.demo.models.Role;
import com.example.demo.models.RoleRepository;
import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            PasswordEncoder passwordEncoder =
                    PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String encodedPassword = passwordEncoder.encode("test");

            Users dimitris = new Users(
                    LocalDate.of(
                            1998,
                            10,
                            21
                    ),
                    "dimgatz98@gmail.com",
                    "dimitris",
                    "dimitrios gkatziouras",
                    encodedPassword
            );

            Users giwrgos = new Users(
                    LocalDate.of(
                            1998,
                            1,
                            1
                    ),
                    "giwrgos98@gmail.com",
                    "giwrgos",
                    "giwrgos papandreou",
                    encodedPassword
            );

            roleRepository.save(new Role("ROLE_USER"));
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_STAFF"));

            userRepository.saveAll(List.of(dimitris, giwrgos));

            userService.addRoleToUser("dimitris", "ROLE_USER");
            userService.addRoleToUser("giwrgos", "ROLE_USER");
        };
    }
}
