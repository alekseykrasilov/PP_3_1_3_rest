package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final RoleServiceImpl roleServiceImpl;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/user")
    public String showUserInfo(Principal principal, Model model) {
        User user = userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
