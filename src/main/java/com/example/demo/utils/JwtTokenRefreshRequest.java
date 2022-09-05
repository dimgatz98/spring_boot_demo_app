package com.example.demo.utils;

import javax.validation.constraints.NotBlank;

public class JwtTokenRefreshRequest {
    @NotBlank
    private String refreshToken;
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
