package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/admin/users")
    public String showAllUsers (Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/admin/{id}")
    public String showOneUser(@PathVariable Long id, Model model) {
        model.addAttribute("user",userService.findUserById(id));
        return "oneUser";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("roles",roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping("/admin/new")
    public String createUser (@ModelAttribute("user") User user, @RequestParam("rolesList") String roles) {
        user.setRoles(roleService.getRole(roles));
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles",roleService.getAllRoles());
        return "editUser";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") Long id, @RequestParam(value="rolesList",required = true) String roles) {
        user.setRoles(roleService.getRole(roles));
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
}
