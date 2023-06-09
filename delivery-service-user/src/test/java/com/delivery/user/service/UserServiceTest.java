package com.delivery.user.service;

import com.delivery.user.config.jwt.JwtTokenProvider;
import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserLoginResponse;
import com.delivery.user.dto.UserSignUpRequest;
import com.delivery.user.dto.UserSignUpResponse;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.validator.UserAuthorizationValidator;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("유저 서비스 테스트")
@DataJpaTest
@Import({JwtTokenProvider.class,
        AuthorityService.class,
        UserAuthorizationValidator.class,
        BCryptPasswordEncoder.class})
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
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

    @Autowired
    private UserAuthorizationValidator userAuthorizationValidator;

    @BeforeEach
    public void setup() {
        this.userService = new UserService(
                userRepository,
                jwtTokenProvider,
                userAuthService,
                authorityService,
                userAuthorizationValidator,
                passwordEncoder);
    }

    @DisplayName("로그인을 할 수 있다")
    @Test
    public void findUserTest() throws Exception {
        // given
        String userId = "user_1@email.com";
        String pw = "abcd123!@dsaf56";
        String userName = "유저1";

        회원가입을_하다(userId, pw, userName);

        Authentication mockedUser = mockAuthentication(userId, pw, List.of("ROLE_USER"));
        UserLoginRequest userLoginRequest = new UserLoginRequest(userId, pw);
        when(userAuthService.getAuthenticationWithAuthorities(any(), any())).thenReturn(mockedUser);

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
        UserSignUpResponse registeredUser = 회원가입을_하다(userId, pw, userName);

        assertEquals(registeredUser.getUserId(), userId);
        assertEquals(registeredUser.getUserName(), userName);

        // password return 없앰으로 인한 제거
        // assertTrue(passwordEncoder.matches(pw, registeredUser.getPassword()));
    }

    private UserSignUpResponse 회원가입을_하다(String userId, String pw, String userName) {
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest(userId, pw, userName);
        return userService.signUp(userSignUpRequest);
    }


    public Authentication mockAuthentication(String userId, String password, List<String> authoritiesStr) {
        Collection<? extends GrantedAuthority> authorities = authoritiesStr.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userId, password, authorities);
    }
}