package com.delivery.user.config.auth;

import com.delivery.user.domain.User;
import com.delivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  security 관련
 *  */

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findOneByUserId(userId))
                .orElseThrow(() -> new UsernameNotFoundException("Not found any userId"));

        List<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityRole().name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }
}
