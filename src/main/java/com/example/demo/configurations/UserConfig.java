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
                    1L,
                    23,
                    LocalDate.of(
                            1998,
                            10,
                            21
                    ),
                    "dimgatz98@gmail.com",
                    "tzourhs",
                    "dimitrios gkatziouras"
            );

            Users giwrgos = new Users(
                    2L,
                    23,
                    LocalDate.of(
                            1998,
                            1,
                            1
                    ),
                    "giwrgos98@gmail.com",
                    "giwrgos",
                    "giwrgos papandreou"
            );

            repository.saveAll(List.of(dimitris, giwrgos));
        };
    }
}
