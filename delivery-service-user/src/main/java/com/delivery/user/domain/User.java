package com.delivery.user.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id")
    private Set<Authority> authorities;

    public User(Long id, String userId, String password, String userName) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }

    public User(Long id, String userId, String password, String userName, Set<Authority> authorities) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.authorities = authorities;
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
}
