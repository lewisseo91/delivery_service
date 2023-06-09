package com.delivery.user.service;

import com.delivery.user.config.jwt.JwtTokenProvider;
import com.delivery.user.domain.Authority;
import com.delivery.user.domain.User;
import com.delivery.user.dto.*;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.validator.UserAuthorizationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
        UserInfoDto checkUser = findUserByUserId(userSignUpRequest.getUserId());

        userAuthorizationValidator.validatePassword(userSignUpRequest);
        userAuthorizationValidator.validateNonExistUser(checkUser);

        List<Authority> authorities = authorityService.saveAllAuthorities(userSignUpRequest.convertFromRoleNameToAuthorityRole());

        User user = new User(userSignUpRequest.getId(),
                userSignUpRequest.getUserId(),
                encodePassword(userSignUpRequest.getPassword()),
                userSignUpRequest.getUserName(),
                new HashSet<>(authorities));

        user.grantAuthorities(authorities);
        User savedUser = addUser(user);

        return UserSignUpResponse.of(savedUser);
    }

    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        UserInfoDto user = findUserByUserId(userLoginRequest.getUserId());

        userAuthorizationValidator.validateLogin(user, userLoginRequest.getPassword());

        Authentication authentication = userAuthService.getAuthenticationWithAuthorities(userLoginRequest, user.getAuthorityNames());
        String accessToken = jwtTokenProvider.createToken(authentication);

        return new UserLoginResponse(accessToken);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Transactional(readOnly = true)
    public UserInfoDto findUserByUserId(String userId) {
        User user = userRepository.findOneByUserId(userId);
        return Optional.ofNullable(user)
                .map(UserInfoDto::of)
                .orElse(null);
    }

    private User addUser(User user) {
        return userRepository.save(user);
    }
}
