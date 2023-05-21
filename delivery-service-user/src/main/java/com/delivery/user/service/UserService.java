package com.delivery.user.service;

import com.delivery.user.config.jwt.JwtTokenProvider;
import com.delivery.user.domain.Authority;
import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.dto.UserLoginResponse;
import com.delivery.user.dto.UserSignUpRequest;
import com.delivery.user.dto.UserSignUpResponse;
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
    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) {
        UserSignUpValidator.validate(userSignUpRequest);

        User checkUser = findUserByUserId(userSignUpRequest.getUserId());
        if (Objects.nonNull(checkUser)) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        List<Authority> authorities = authorityService.findAllAuthoritiesByAuthorityName(userSignUpRequest.convertFromRoleNameToAuthorityRole());

        User user = new User(userSignUpRequest.getId(),
                userSignUpRequest.getUserId(),
                passwordEncoder.encode(userSignUpRequest.getPassword()),
                userSignUpRequest.getUserName(),
                new HashSet<>(authorities));

        return UserSignUpResponse.of(addUser(user));
    }

    private User findUserByUserId(String userId) {
        return userRepository.findOneByUserId(userId);
    }

    private User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = findUserByUserId(userLoginRequest.getUserId());

        if (Objects.isNull(user)) {
            throw new RuntimeException("이미 가입되어 있지 않은 유저입니다.");
        }

        if (!passwordEncoder.matches(user.getPassword(), userLoginRequest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        Authentication authentication = userAuthService.getAuthentication(userLoginRequest);
        String accessToken = jwtTokenProvider.createToken(authentication);

        return new UserLoginResponse(accessToken);
    }
}
