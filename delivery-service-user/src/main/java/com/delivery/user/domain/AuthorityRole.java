package com.delivery.user.domain;

import org.h2.util.StringUtils;

import java.util.Arrays;

public enum AuthorityRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_RIDER("ROLE_RIDER");

    private final String roleName;

    AuthorityRole(String roleName) {
        this.roleName = roleName;
    }

    public static AuthorityRole getAuthorityRoleByRoleName(String roleName) {
        if (StringUtils.isNullOrEmpty(roleName)) {
            throw new RuntimeException("role name is empty");
        }

        return Arrays.stream(AuthorityRole.values())
                .filter(authorityRole -> authorityRole.roleName.equals(roleName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("role name is wrong"));
    }
}
