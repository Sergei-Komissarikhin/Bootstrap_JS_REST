package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.UserRepository;
import com.sergei.spring.boot.model.Role;
import com.sergei.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RESTUserServiceImpl implements RESTUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setRoles(getRoles(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setRoles(getRoles(user));
        if (!user.getPassword().startsWith("$2a$08$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.updateUser(user);
    }

    private Set<Role> getRoles(User user) {
        Set<Role> updateRoles = new HashSet<>();
        for (Role role : user.getRoles()) {
            updateRoles.add(roleService.getRoleByName(role.getRole()));
        }
        return updateRoles;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
