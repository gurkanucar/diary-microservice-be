package com.iknow.apigateway.requestService;


import com.iknow.apigateway.model.User;
import com.iknow.apigateway.requestModel.RegisterUserRequest;
import com.iknow.apigateway.requestModel.UpdateUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user-microservice",
        path = "/user",
        //url = "http://localhost:8081",
        //url = "${user-microservice.url}",
        configuration = FeignConfiguration.class)
public interface UserRequestService {

    @GetMapping("/all")
    List<User> getAllUsers();

    @GetMapping("/{id}")
    User getUserByID(@PathVariable Long id);

    @GetMapping("/username/{username}")
    User getUserByUsername(@PathVariable String username);

    @PutMapping
    User updateUser(@RequestBody UpdateUserRequest requestBody);

    @PostMapping
    User createUser(@RequestBody RegisterUserRequest requestBody);

    @DeleteMapping("/user/{username}")
    void deleteUserByUsername(@PathVariable String username);

    @PutMapping("/role")
    User grantRole(@RequestBody Object request);

    @DeleteMapping("/role")
    User revokeRole(@RequestBody Object request);


}