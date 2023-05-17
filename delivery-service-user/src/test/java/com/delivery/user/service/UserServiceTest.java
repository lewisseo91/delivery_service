package com.delivery.user.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("유저 서비스 테스트")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @DisplayName("회원가입을 할 수 있다")
    @Test
    public void signUpUserTest() {

    }

    @DisplayName("회원가입은 대문자, 소문자, 숫자, 특수문자 중 3종류 이상을 만족한다.")
    @Test
    public void signUpUserValid1Test() {

    }

    @DisplayName("회원가입은 12자리 이상 문자열을 만족한다.")
    @Test
    public void signUpUserValid2Test() {

    }

    @DisplayName("회원을 조회 할 수 있다")
    @Test
    public void findUserTest() {

    }
}