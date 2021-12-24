package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.JpaRoleRepository;
import com.sergei.spring.boot.dao.JpaUserRepository;
import com.sergei.spring.boot.model.Role;
import com.sergei.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RESTUserServiceImpl implements RESTUserService {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Autowired
    JpaRoleRepository jpaRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Override
    public List<User> getAllUsers() {
        return jpaUserRepository.findAll();
    }


    @Override
    public User getUserById(Long id) {
        return jpaUserRepository.findById(id).orElse(null);
    }

    @Override
    public void addUser(User user) {
        user.setRoles(getRoles(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jpaUserRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        user.setRoles(getRoles(user));
        if (!user.getPassword().startsWith("$2a$08$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        jpaUserRepository.save(user);
    }

    private Set<Role> getRoles(User user) {
        Set<Role> updateRoles = new HashSet<>();
        for (Role role : user.getRoles()) {
            updateRoles.add(jpaRoleRepository.findByRole(role.getRole()));
        }
        return updateRoles;
    }

    @Override
    public void deleteUser(Long id) {
        jpaUserRepository.deleteById(id);
    }
}
