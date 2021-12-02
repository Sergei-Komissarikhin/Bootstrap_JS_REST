package com.sergei.spring.boot.service;

import com.sergei.spring.boot.dao.UserDao;
import com.sergei.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByName(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
