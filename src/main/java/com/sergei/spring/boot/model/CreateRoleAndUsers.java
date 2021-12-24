package com.sergei.spring.boot.model;

import com.sergei.spring.boot.dao.JpaRoleRepository;
import com.sergei.spring.boot.service.RESTUserService;
import com.sergei.spring.boot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class CreateRoleAndUsers {
    @Autowired
    private RESTUserService userService;
    @Autowired
    private JpaRoleRepository roleService;



    @PostConstruct
    void createUsers() {

        roleService.save(new Role("ADMIN"));
        roleService.save(new Role("USER"));

        userService.addUser(new User("Sergei", "Ivanov", 37,
                "sus@mail.ru", "1234",
                Set.of(roleService.findByRole("ADMIN"), roleService.findByRole("USER"))));

        userService.addUser(new User("Anna", "Ivanova", 37,
                "asu@ya.ru", "1234",
                Set.of(roleService.findByRole("ADMIN"))));

        userService.addUser(new User("Dima", "Ivanov", 10,
                "dima@ya.ru", "12345",
                Set.of(roleService.findByRole("USER"))));

        userService.addUser(new User("Lesha", "Ivanov", 7,
                "lesha@ya.ru", "1234567",
                Set.of(roleService.findByRole("USER"))));
    }
}