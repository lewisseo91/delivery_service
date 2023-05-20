package com.delivery.user.dto;

public class UserLoginResponse {
    private String accessToken;

    public UserLoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
