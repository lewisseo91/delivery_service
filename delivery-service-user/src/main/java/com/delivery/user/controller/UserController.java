package com.delivery.user.controller;

import com.delivery.user.domain.AuthorityRole;
import com.delivery.user.dto.*;
import com.delivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/auth/test")
    @Secured(AuthorityRole.RoleName.USER)
    public ResponseEntity<String> authTest(@AuthenticationPrincipal User user) {
        System.out.println(user.getUsername() + " " + user.getAuthorities());
        return ResponseEntity.ok(userService.findUserByUserId(user.getUsername()).toString());
    }

    @GetMapping("/user/{userId}")
    @Secured(AuthorityRole.RoleName.USER)
    public ResponseEntity<UserInfoDto> getUserInfoByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(userService.findUserByUserId(userId));
    }
}
