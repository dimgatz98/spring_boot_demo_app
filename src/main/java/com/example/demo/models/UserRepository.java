package com.example.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<Users, Long> {
    @Query("SELECT u from Users u WHERE u.username = ?1")
    Optional<Users> findByUsername(String username);
}
