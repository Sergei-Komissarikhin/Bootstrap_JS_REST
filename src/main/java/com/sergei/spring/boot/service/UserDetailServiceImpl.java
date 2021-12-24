package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.JpaUserRepository;
import com.sergei.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = jpaUserRepository.queryUsersByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
