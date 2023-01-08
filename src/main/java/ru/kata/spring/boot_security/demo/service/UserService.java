package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public User findUserById(Long id);

    public List<User> getAllUsers ();

    public User findByUsername (String username);

    public User findByEmail(String email);

    public boolean saveUser (User user);

    public boolean deleteById (Long id);

    public void updateUser(User user);
}
