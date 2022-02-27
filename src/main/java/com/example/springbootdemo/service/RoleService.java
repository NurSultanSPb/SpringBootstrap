package com.example.springbootdemo.service;


import com.example.springbootdemo.model.Role;

import java.util.HashSet;
import java.util.List;

public interface RoleService {
    Role getByName(String name);
    List<Role> getAllRoles();
    void addRole(Role role);
    HashSet<Role> getSetRoles(String[] roleNames);
}
