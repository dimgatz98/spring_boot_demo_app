package com.example.demo.models;

import java.time.Instant;
import javax.persistence.*;
@Entity(name = "refreshToken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users user;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Instant expiryDate;

    public RefreshToken() {}

    public RefreshToken(Users user, String token, Instant expiryDate) {
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public long getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}