package com.sergei.spring.boot.controller;

import com.sergei.spring.boot.dao.UserRepository;
import com.sergei.spring.boot.model.User;
import com.sergei.spring.boot.service.RESTUserService;
import com.sergei.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAdminController {
    @Autowired
    RESTUserService restUserService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return restUserService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        return restUserService.getUserById(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        restUserService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        restUserService.deleteUser(id);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
        restUserService.updateUser(user);
    }

}
