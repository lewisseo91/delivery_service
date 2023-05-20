package com.delivery.user.service;

import com.delivery.user.domain.Authority;
import com.delivery.user.domain.AuthorityRole;
import com.delivery.user.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public List<Authority> findAllAuthoritiesByAuthorityName(Set<AuthorityRole> authorityRoles) {
        return authorityRepository.findAllByAuthorityRoleIn(authorityRoles);
    }
}
