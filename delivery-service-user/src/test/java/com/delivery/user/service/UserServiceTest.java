package com.delivery.user.service;

import com.delivery.user.config.jwt.JwtTokenProvider;
import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserLoginResponse;
import com.delivery.user.dto.UserSignUpRequest;
import com.delivery.user.dto.UserSignUpResponse;
import com.delivery.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("유저 서비스 테스트")
@DataJpaTest
@Import({JwtTokenProvider.class,
        AuthorityService.class,
        BCryptPasswordEncoder.class})
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserAuthService userAuthService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @BeforeEach
    public void setup() {
        this.userService = new UserService(
                userRepository,
                jwtTokenProvider,
                userAuthService,
                authorityService,
                passwordEncoder);
    }

    @DisplayName("로그인을 할 수 있다")
    @Test
    public void findUserTest() throws Exception {
        // given
        String userId = "user_1@email.com";
        String pw = "abcd123!@dsaf56";
        Authentication mockedUser = mockAuthentication(userId, pw, List.of("ROLE_USER"));
        UserLoginRequest userLoginRequest = new UserLoginRequest(userId, pw);
        when(userAuthService.getAuthentication(any())).thenReturn(mockedUser);

        // when
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);


        // then
        assertEquals(userLoginResponse.getAccessToken(), jwtTokenProvider.createToken(mockedUser));
    }


    @DisplayName("회원가입을 할 수 있다")
    @Test
    public void signUpUserTest() {
        String userId = "user_1@email.com";
        String pw = "abcd123!@dsaf56";
        String userName = "유저1";
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest(userId, pw, userName);
        UserSignUpResponse registeredUser = userService.signUp(userSignUpRequest);

        assertEquals(registeredUser.getUserId(), userId);
        assertEquals(registeredUser.getUserName(), userName);

        // password return 없앰으로 인한 제거
        // assertTrue(passwordEncoder.matches(pw, registeredUser.getPassword()));
    }

    public Authentication mockAuthentication(String userId, String password, List<String> authoritiesStr) {
        Collection<? extends GrantedAuthority> authorities = authoritiesStr.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userId, password, authorities);
    }
}