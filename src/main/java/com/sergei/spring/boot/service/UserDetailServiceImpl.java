package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.UserRepository;
import com.sergei.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.loadUserByUsername(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
