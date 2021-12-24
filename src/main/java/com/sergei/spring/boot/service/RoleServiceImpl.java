package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.JpaRoleRepository;
import com.sergei.spring.boot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private JpaRoleRepository jpaRoleRepository;

    @Override
    @Transactional
    public void addRole(Role role) {
        jpaRoleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return jpaRoleRepository.findByRole(name);
    }

    @Override
    public Set<Role> getRoles() {
        return new HashSet<>(jpaRoleRepository.findAll());
    }
}
