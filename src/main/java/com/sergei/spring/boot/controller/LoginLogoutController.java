package com.sergei.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(){
        return "redirect:/admin";
    }
}
