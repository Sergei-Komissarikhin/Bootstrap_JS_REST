package com.sergei.spring.boot.service;

import com.sergei.spring.boot.model.Role;
import com.sergei.spring.boot.model.User;

import java.util.List;
import java.util.Set;

public interface RESTUserService  {
    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);
}
