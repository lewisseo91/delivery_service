package com.delivery.user.dto;

import com.delivery.user.domain.AuthorityRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSignUpRequest {
    private Long id;

    private String userId;

    private String password;

    private String nickname;

    private List<String> authorityNames;

    public UserSignUpRequest() {
    }

    public UserSignUpRequest(String userId, String password, String nickname) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.authorityNames = new ArrayList<>();
    }

    public UserSignUpRequest(Long id, String userId, String password, String nickname, List<String> authorityNames) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.authorityNames = authorityNames;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public Set<AuthorityRole> convertFromRoleNameToAuthorityRole() {
        return Optional.ofNullable(authorityNames)
                .map(authorityNames -> authorityNames.stream()
                        .map(AuthorityRole::getAuthorityRoleByRoleName)
                        .collect(Collectors.toSet()))
            .orElse(Set.of(AuthorityRole.ROLE_USER));
    }
}
