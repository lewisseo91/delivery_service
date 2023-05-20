package com.delivery.user.service;

import com.delivery.user.config.jwt.JwtTokenProvider;
import com.delivery.user.domain.Authority;
import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserLoginResponse;
import com.delivery.user.dto.UserSignUpRequest;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.validator.UserSignUpValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserAuthService userAuthService;

    private final AuthorityService authorityService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signUp(UserSignUpRequest userSignUpRequest) {
        UserSignUpValidator.validate(userSignUpRequest);
        if (Objects.nonNull(userRepository.findOneByUserId(userSignUpRequest.getUserId()))) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        List<Authority> authorities = authorityService.findAllAuthoritiesByAuthorityName(userSignUpRequest.convertFromRoleNameToAuthorityRole());

        User user = new User(userSignUpRequest.getId(),
                userSignUpRequest.getUserId(),
                passwordEncoder.encode(userSignUpRequest.getPassword()),
                userSignUpRequest.getNickname(),
                new HashSet<>(authorities));

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
