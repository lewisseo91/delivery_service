package com.delivery.user.service;

import com.delivery.user.config.jwt.JwtTokenProvider;
import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserLoginResponse;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.validator.UserSignUpValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final UserAuthService userAuthService;

    @Transactional
    public User signUp(User user) {
        UserSignUpValidator.validate(user);

        return addUser(user);
    }

    private User addUser(User user) {
        return userRepository.save(user);
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Authentication authentication = userAuthService.getAuthentication(userLoginRequest);
        String accessToken = jwtTokenProvider.createToken(authentication);

        return new UserLoginResponse(accessToken);
    }
}
