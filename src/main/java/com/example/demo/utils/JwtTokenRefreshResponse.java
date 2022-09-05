package com.example.demo.utils;

public class JwtTokenRefreshResponse {
    private String accessToken;
    private String refreshToken;

    public JwtTokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}