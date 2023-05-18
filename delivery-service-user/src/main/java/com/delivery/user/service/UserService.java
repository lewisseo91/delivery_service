package com.delivery.user.service;

import com.delivery.user.domain.User;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.validator.UserSignUpValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User signUp(User user) {
        UserSignUpValidator.validate(user);

        return addUser(user);
    }

    private User addUser(User user) {
        return userRepository.save(user);
    }

    public void login(UserLoginRequest userLoginRequest) {
    }
}
