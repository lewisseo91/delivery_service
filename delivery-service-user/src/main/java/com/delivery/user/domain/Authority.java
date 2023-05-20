package com.delivery.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id")
    private Long authorityId;

    @Column(name = "authority_name")
    @Enumerated(value = EnumType.STRING)
    private AuthorityRole authorityRole;

    public Authority() {
    }

    public Authority(AuthorityRole authorityRole) {
        this.authorityRole = authorityRole;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public AuthorityRole getAuthorityRole() {
        return authorityRole;
    }
}