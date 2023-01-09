package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleService {

    public Set<Role> getAllRoles();

    public Set<Role> getRole(String role);
}
