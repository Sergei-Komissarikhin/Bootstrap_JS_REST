package com.sergei.spring.boot.controller;

import com.sergei.spring.boot.service.RoleService;
import com.sergei.spring.boot.service.UserDetailServiceImpl;
import com.sergei.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("")
public class UserController {
    private final UserDetailServiceImpl userService;
    @Autowired
    private UserService allUserService;

    @Autowired
    public UserController(UserDetailServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printWelcome() {
        return "/hello";
    }


    @GetMapping("/admin_panel")
    public String admin_panel(Model model) {
        model.addAttribute("users", allUserService.getAllUsers());
        return "admin_panel";
    }

    @GetMapping("/user/show")
    public String userShowPage(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "user/show";
    }
}