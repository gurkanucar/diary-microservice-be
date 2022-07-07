package com.iknow.apigateway;

import com.iknow.apigateway.model.ROLES;
import com.iknow.apigateway.model.Role;
import com.iknow.apigateway.model.User;
import com.iknow.apigateway.requestModel.RegisterUserRequest;
import com.iknow.apigateway.service.RoleService;
import com.iknow.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@EnableFeignClients
@SpringBootApplication
public class ApiGatewayApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;

	@Override
	public void run(String... args) throws Exception {
/*
		roleService.saveRole(Role.builder().name("USER").detail("simple operations").build());
		roleService.saveRole(Role.builder().name("ADMIN").detail("admin operations").build());
		roleService.saveRole(Role.builder().name("SUPERADMIN").detail("super operations").build());
		//User superadmin = userService.createUser(RegisterUserRequest.builder().username("gurkan").name("Gurkan").surname("UCAR").email("g@mail.com").password("password").build());
		userService.createUser(RegisterUserRequest.builder().username("metin").email("m@mail.com").name("Metin").surname("OZDEMIR").password("password").build());
		userService.createUser(RegisterUserRequest.builder().username("ali").email("a@mail.com").name("Ali").surname("OZDEMIR").password("password").build());
		userService.createUser(RegisterUserRequest.builder().username("kartal").email("k@mail.com").name("Kartal").surname("yok").password("password").build());
		User admin = userService.createUser(RegisterUserRequest.builder().username("sezai").name("Sezai").surname("YANLIZ").email("s@mail.com").password("password").build());


		var superAdminRole = roleService.getRoleByName(ROLES.SUPERADMIN.toString());
		//superadmin.setRoles(List.of(superAdminRole));
		//userService.updateUser(superadmin);

		var adminRole = roleService.getRoleByName(ROLES.ADMIN.toString());
		admin.setRoles(List.of(adminRole));
		userService.updateUser(admin);*/


	}
}
