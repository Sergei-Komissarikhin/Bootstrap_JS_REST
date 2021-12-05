package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.UserDao;
import com.sergei.spring.boot.model.Role;
import com.sergei.spring.boot.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final RoleService roleService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void addUser(User user, Set<Role> roles) {
        user.setRoles(getRolesForUpdate(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, Set<Role> roles) {
        user.setRoles(getRolesForUpdate(roles));
        if(user.getPassword().startsWith("$2a$08")){
            userDao.updateUser(user);
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.updateUser(user);
        }
    }

    public Set<Role> getRolesForUpdate(Set<Role> roles) {
        Set<Role> updateRoles = new HashSet<>();
        for (Role role : roles) {
            updateRoles.add(roleService.getRoleByName(role.getRole()));
        }
        return updateRoles;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
