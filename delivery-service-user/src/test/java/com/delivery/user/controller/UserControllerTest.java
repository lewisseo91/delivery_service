package com.delivery.user.controller;

import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserSignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("유저 컨트롤러 테스트")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    private String userId;
    private String password;
    private String userName;

    @BeforeEach
    public void setup() {
        userId = "userId_1";
        password = "Adfslka!20dsafd_$$";
        userName = "user_111";
    }

    @DisplayName("회원 가입을 할 수 있다.")
    @Test
    public void signUpTest() throws Exception {

        UserSignUpRequest userSignUpRequest = new UserSignUpRequest(userId, password, userName);
        String content = new ObjectMapper().writeValueAsString(userSignUpRequest);

        mvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userId)))
                .andExpect(content().string(containsString(userName)))
                .andDo(print());
    }

    @DisplayName("로그인을 할 수 있다.")
    @Test
    public void loginTest() throws Exception {
        signUpTest();
        UserLoginRequest userLoginRequest = new UserLoginRequest(userId, password);
        String content = new ObjectMapper().writeValueAsString(userLoginRequest);

        mvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("accessToken")))
                .andDo(print());
    }
}