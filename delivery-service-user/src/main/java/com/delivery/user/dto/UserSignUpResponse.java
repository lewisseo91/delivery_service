package com.delivery.user.dto;

import com.delivery.user.domain.AuthorityRole;
import com.delivery.user.domain.User;

import java.util.Set;

public class UserSignUpResponse {
    private Long id;

    private String userId;

    private String userName;

    private Set<AuthorityRole> authorityNames;

    public UserSignUpResponse() {
    }

    public UserSignUpResponse(Long id, String userId, String userName, Set<AuthorityRole> authorityNames) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.authorityNames = authorityNames;
    }

    public static UserSignUpResponse of(User user) {
        return new UserSignUpResponse(
                user.getId(),
                user.getUserId(),
                user.getUserName(),
                user.getAuthorityNames());
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Set<AuthorityRole> getAuthorityNames() {
        return authorityNames;
    }
}
