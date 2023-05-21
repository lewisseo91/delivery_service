package com.delivery.user.controller;

import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserLoginResponse;
import com.delivery.user.dto.UserSignUpRequest;
import com.delivery.user.dto.UserSignUpResponse;
import com.delivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.login(userLoginRequest);
    }

    @PostMapping("/signup")
    public UserSignUpResponse signup(@RequestBody UserSignUpRequest userSignUpRequest) {
        return userService.signUp(userSignUpRequest);
    }
}
