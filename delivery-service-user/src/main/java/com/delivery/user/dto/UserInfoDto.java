package com.delivery.user.dto;

import com.delivery.user.domain.AuthorityRole;
import com.delivery.user.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserInfoDto {
    private Long id;

    private String userId;

    private String userName;

    private String password;

    private Set<AuthorityRole> authorityRoles;

    private Set<String> authorityNames;

    public UserInfoDto() {
    }

    public UserInfoDto(Long id, String userId, String password, String userName, Set<AuthorityRole> authorityRoles) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.authorityRoles = authorityRoles;
        this.authorityNames = authorityRoles.stream().map(AuthorityRole::getRoleName).collect(Collectors.toSet());
    }

    public static UserInfoDto of(User user) {
        return new UserInfoDto(user.getId(), user.getUserId(), user.getPassword(), user.getUserName(), user.getAuthorityRoles());
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

    public Set<AuthorityRole> getAuthorityRoles() {
        return authorityRoles;
    }

    public Set<String> getAuthorityNames() {
        return authorityNames;
    }

    public String getPassword() {
        return password;
    }
}
