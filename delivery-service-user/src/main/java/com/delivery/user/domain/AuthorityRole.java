package com.delivery.user.domain;

import org.h2.util.StringUtils;

import java.util.Arrays;

import static com.delivery.user.domain.AuthorityRole.RoleName.USER;
import static com.delivery.user.domain.AuthorityRole.RoleName.ADMIN;
import static com.delivery.user.domain.AuthorityRole.RoleName.RIDER;

public enum AuthorityRole {
    ROLE_USER(USER),
    ROLE_ADMIN(ADMIN),
    ROLE_RIDER(RIDER);

    public static class RoleName {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String RIDER = "ROLE_RIDER";
    }

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

    public String getRoleName() {
        return roleName;
    }
}
