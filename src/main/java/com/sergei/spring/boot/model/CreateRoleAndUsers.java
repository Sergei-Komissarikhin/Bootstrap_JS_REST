package com.sergei.spring.boot.model;

import com.sergei.spring.boot.service.RESTUserService;
import com.sergei.spring.boot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class CreateRoleAndUsers {
    private final RESTUserService userService;
    private final RoleService roleService;

    @Autowired
    public CreateRoleAndUsers(RESTUserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    void createUsers() {

        roleService.addRole(new Role("ADMIN"));
        roleService.addRole(new Role("USER"));

        userService.addUser(new User("Sergei", "Ivanov", 37,
                "sus@mail.ru", "1234",
                Set.of(roleService.getRoleByName("ADMIN"), roleService.getRoleByName("USER"))));

        userService.addUser(new User("Anna", "Ivanova", 37,
                "asu@ya.ru", "1234",
                Set.of(roleService.getRoleByName("USER"))));
    }
}