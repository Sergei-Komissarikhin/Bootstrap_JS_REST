package com.sergei.spring.boot.dao;


import com.sergei.spring.boot.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void delete(Long id);

    void updateUser(User user);

    User loadUserByUsername(String email);
}
