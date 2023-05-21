package com.delivery.user.service;

import com.delivery.user.config.jwt.JwtTokenProvider;
import com.delivery.user.domain.Authority;
import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserLoginResponse;
import com.delivery.user.dto.UserSignUpRequest;
import com.delivery.user.dto.UserSignUpResponse;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.validator.UserAuthorizationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserAuthService userAuthService;

    private final AuthorityService authorityService;

    private final UserAuthorizationValidator userAuthorizationValidator;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) {
        User checkUser = findUserByUserId(userSignUpRequest.getUserId());

        userAuthorizationValidator.validatePassword(userSignUpRequest);
        userAuthorizationValidator.validateNonExistUser(checkUser);

        List<Authority> authorities = authorityService.findAllAuthoritiesByAuthorityName(userSignUpRequest.convertFromRoleNameToAuthorityRole());

        User user = new User(userSignUpRequest.getId(),
                userSignUpRequest.getUserId(),
                encodePassword(userSignUpRequest.getPassword()),
                userSignUpRequest.getUserName(),
                new HashSet<>(authorities));

        return UserSignUpResponse.of(addUser(user));
    }

    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = findUserByUserId(userLoginRequest.getUserId());

        userAuthorizationValidator.validateLogin(user, userLoginRequest.getPassword());

        Authentication authentication = userAuthService.getAuthentication(userLoginRequest);
        String accessToken = jwtTokenProvider.createToken(authentication);

        return new UserLoginResponse(accessToken);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private User findUserByUserId(String userId) {
        return userRepository.findOneByUserId(userId);
    }

    private User addUser(User user) {
        return userRepository.save(user);
    }
}
