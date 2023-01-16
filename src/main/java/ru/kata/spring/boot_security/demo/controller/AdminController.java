package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
//@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

    private final RoleServiceImpl roleServiceImpl;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model, Principal principal) {
        User admin = userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("users", userServiceImpl.getAllUsers());
        model.addAttribute("admin", admin);
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "admin";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("rolesList") String roles) {
        user.setRoles(roleServiceImpl.getRole(roles));
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") Long id, @RequestParam(value = "rolesList", required = true) String roles) {
        user.setRoles(roleServiceImpl.getRole(roles));
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }
}
