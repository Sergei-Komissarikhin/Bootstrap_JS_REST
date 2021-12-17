package com.sergei.spring.boot.controller;

import com.sergei.spring.boot.service.RoleService;
import com.sergei.spring.boot.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final RoleService roleService;
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public AdminController(RoleService roleService, UserDetailServiceImpl userDetailService) {
        this.roleService = roleService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("")
    public String index(Model model, Principal principal) {
        model.addAttribute("principal", userDetailService.loadUserByUsername(principal.getName()));
        model.addAttribute("roles",roleService.getRoles());
        return "admin/index";
    }
}