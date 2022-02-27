package com.example.springbootdemo.controllers;

import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.RoleService;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(@AuthenticationPrincipal UserDetails userDetails,
                              Model model) {
        String username = userDetails.getUsername();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "newView";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("userNew") @Valid User person,
                         @RequestParam(value = "roleNames") String[] roleNames) {
        userService.saveUser(person, roleNames);
        return "redirect:/admin";
    }

    @PatchMapping("edit/{id}")
    public String update(@ModelAttribute("user") @Valid User person,
                         @PathVariable("id") int id,
                         @RequestParam(value = "nameRoles") String[] roleNames) {
        userService.update(id, person, roleNames);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
