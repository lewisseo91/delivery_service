package com.delivery.user.service;

import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("유저 서비스 테스트")
@DataJpaTest
@Import(UserService.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @DisplayName("회원가입을 할 수 있다")
    @Test
    public void signUpUserTest() {
        Long id = 1L;
        String userId = "user_1@email.com";
        String pw = "abcd123!@dsaf56";
        String userName = "유저1";
        User user = new User(id, userId, pw, userName);
        User registeredUser = userService.signUp(user);

        assertEquals(registeredUser.getUserId(), userId);
        assertEquals(registeredUser.getPassword(), pw);
        assertEquals(registeredUser.getUserName(), userName);
    }

    @DisplayName("로그인을 할 수 있다")
    @Test
    public void findUserTest() {
        signUpUserTest();

        String userId = "user_1@email.com";
        String pw = "abcd123!@dsaf56";
        UserLoginRequest userLoginRequest = new UserLoginRequest(userId, pw);
        userService.login(userLoginRequest);
    }
}