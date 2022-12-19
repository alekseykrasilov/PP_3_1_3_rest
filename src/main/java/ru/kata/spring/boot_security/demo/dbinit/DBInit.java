package ru.kata.spring.boot_security.demo.dbinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBInit {

    UserService userService;
    RoleService roleService;
    RoleRepository roleRepository;

    @Autowired
    public DBInit(UserService userService, RoleService roleService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void initDB () {
        Set<Role> rolesList = new HashSet<>();
        Role role1 = new Role(1L, "ROLE_USER");
        Role role2 = new Role(2L,"ROLE_ADMIN" );
        rolesList.add(role1);
        rolesList.add(role2);
        //password: user1
        User user1 = new User(1L, "User1", "user1", "user1"
                , 25, "user1@mail.ru");
        //password: user2
        User user2 = new User(2L, "User2", "user2", "user2"
                , 19, "user2@mail.ru");
        User user3 = new User(3L,"User3","user3","user3",26,"user3@mail.ru");
        user1.setRoles(Collections.singleton(role2));
        user2.setRoles(Collections.singleton(role1));
        user3.setRoles(rolesList);
        roleRepository.save(role1);
        roleRepository.save(role2);
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}
