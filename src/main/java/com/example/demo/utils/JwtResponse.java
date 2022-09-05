package com.example.demo.utils;

public class JwtResponse {
    private String jwt;
    private String refreshToken;

    public String getJwt () {
        return jwt;
    }

    public String getRefreshToken () { return refreshToken; }

    public JwtResponse(String jwt, String refreshToken) {
        this.jwt = jwt;
        this.refreshToken = refreshToken;
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
