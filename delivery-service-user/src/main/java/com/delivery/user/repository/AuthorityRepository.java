package com.delivery.user.repository;

import com.delivery.user.domain.Authority;
import com.delivery.user.domain.AuthorityRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    List<Authority> findAllByAuthorityRoleIn(Collection<AuthorityRole> authorityRoles);

}
