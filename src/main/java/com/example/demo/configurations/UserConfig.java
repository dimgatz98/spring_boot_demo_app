package com.example.demo.configurations;

import com.example.demo.models.UserRepository;
import com.example.demo.models.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            Users dimitris = new Users(
                    LocalDate.of(
                            1998,
                            10,
                            21
                    ),
                    "dimgatz98@gmail.com",
                    "tzourhs",
                    "dimitrios gkatziouras",
                    "test"
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
                    "test"
            );

            repository.saveAll(List.of(dimitris, giwrgos));
        };
    }
}
