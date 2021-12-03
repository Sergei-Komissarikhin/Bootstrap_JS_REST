package com.sergei.spring.boot.controller;


import com.sergei.spring.boot.model.Role;
import com.sergei.spring.boot.model.User;
import com.sergei.spring.boot.service.RoleService;
import com.sergei.spring.boot.service.UserDetailServiceImpl;
import com.sergei.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserDetailServiceImpl userDetailService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("")
    public String index(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("user1", userDetailService.loadUserByUsername(principal.getName()));
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show/";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "admin/new";
    }


    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("rolesForUser") Set<Role> roles) {
        userService.addUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") long id,
                       Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "admin/edit";
    }

    @PatchMapping("{id}")
    public String update(@PathVariable("id") long id,
                         @ModelAttribute("user") User user,
                         @RequestParam("rolesForUser") Set<Role> roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}