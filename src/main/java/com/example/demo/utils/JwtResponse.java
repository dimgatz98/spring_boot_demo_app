package com.example.demo.utils;

public class JwtResponse {
    private String jwt;

    public String getJwt () {
        return jwt;
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
