package com.sergei.spring.boot.controller;

import com.sergei.spring.boot.model.User;
import com.sergei.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAdminController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
