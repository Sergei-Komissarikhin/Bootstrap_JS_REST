package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.UserRepository;
import com.sergei.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RESTUserServiceImpl implements RESTUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if(!user.getPassword().startsWith("$2a$08$")){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
