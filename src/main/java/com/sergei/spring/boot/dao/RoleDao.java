package com.sergei.spring.boot.dao;


import com.sergei.spring.boot.model.Role;

import java.util.Set;

public interface RoleDao {

    void addRole(Role role);

    Role getRoleByName(String name);

    Set<Role> getRoles();
}
