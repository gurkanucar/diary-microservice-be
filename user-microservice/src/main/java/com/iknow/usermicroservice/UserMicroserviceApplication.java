package com.iknow.usermicroservice;

import com.iknow.usermicroservice.model.ROLES;
import com.iknow.usermicroservice.model.Role;
import com.iknow.usermicroservice.model.User;
import com.iknow.usermicroservice.request.RegisterUserRequest;
import com.iknow.usermicroservice.service.RoleService;
import com.iknow.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UserMicroserviceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }


    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {


        roleService.saveRole(Role.builder().name("USER").detail("simple operations").build());
        roleService.saveRole(Role.builder().name("ADMIN").detail("admin operations").build());
        roleService.saveRole(Role.builder().name("SUPERADMIN").detail("super operations").build());
        User superadmin = userService.createUser(RegisterUserRequest.builder().username("gurkan").email("g@mail.com").password("password").build());
        userService.createUser((RegisterUserRequest.builder().username("metin").email("m@mail.com").password("password").build()));
        userService.createUser((RegisterUserRequest.builder().username("ali").email("a@mail.com").password("password").build()));
        userService.createUser((RegisterUserRequest.builder().username("kartal").email("k@mail.com").password("password").build()));
        User admin = userService.createUser((RegisterUserRequest.builder().username("sezai").email("s@mail.com").password("password").build()));


        var superAdminRole = roleService.getRoleByName(ROLES.SUPERADMIN.toString());
        superadmin.setRoles(List.of(superAdminRole));
        userService.updateUser(superadmin);

        var adminRole = roleService.getRoleByName(ROLES.ADMIN.toString());
        admin.setRoles(List.of(adminRole));
        userService.updateUser(admin);

    }
}
