package com.example.springbootdemo.DAO;

import com.example.springbootdemo.model.Role;

import java.util.List;
import java.util.Set;

public interface RolesDAO {
    Role getByName(String name);
    List<Role> getAllRoles();
    void addRole(Role role);
}
