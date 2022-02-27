package com.example.springbootdemo.controllers;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails,
                              Model model){
        String username = userDetails.getUsername();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "userPage";
    }
}
