package com.sergei.spring.boot.dao;

import com.sergei.spring.boot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
