package com.sergei.spring.boot.controller;

import com.sergei.spring.boot.model.User;
import com.sergei.spring.boot.service.RESTUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAdminController {
    @Autowired
    RESTUserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

}
