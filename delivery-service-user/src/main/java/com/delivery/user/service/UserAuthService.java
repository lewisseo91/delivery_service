package com.delivery.user.service;

import com.delivery.user.domain.Authority;
import com.delivery.user.domain.AuthorityRole;
import com.delivery.user.dto.UserLoginRequest;
import com.delivery.user.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserAuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Authentication getAuthentication(UserLoginRequest userLoginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUserId(), userLoginRequest.getPassword());

        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }
}
