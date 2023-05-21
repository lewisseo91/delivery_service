package com.delivery.user.service;

import com.delivery.user.domain.Authority;
import com.delivery.user.domain.AuthorityRole;
import com.delivery.user.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public List<Authority> saveAllAuthorities(Set<AuthorityRole> authorities) {
        Set<Authority> newAuthorities = authorities.stream().map(Authority::new).collect(Collectors.toSet());
        return authorityRepository.saveAll(newAuthorities);
    }
}
