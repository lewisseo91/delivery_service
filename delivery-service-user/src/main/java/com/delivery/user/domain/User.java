package com.delivery.user.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Authority> authorities;

    public User(Long id, String userId, String password, String userName) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.authorities = new HashSet<>();
    }

    public User(Long id, String userId, String password, String userName, Set<Authority> authorities) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.authorities = authorities;
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

    public String getUserName() {
        return userName;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public Set<AuthorityRole> getAuthorityRoles() {
        return authorities.stream()
                .map(Authority::getAuthorityRole)
                .collect(Collectors.toSet());
    }

    public Set<String> getAuthorityNames() {
        return authorities.stream()
                .map(Authority::getAuthorityRole)
                .map(AuthorityRole::getRoleName)
                .collect(Collectors.toSet());
    }

    public void grantAuthorities(List<Authority> authorities) {
        this.getAuthorities().addAll(authorities);

        this.authorities.stream().forEach(authority -> authority.updateUser(this));
    }
}
