package com.iknow.apigateway.requestService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "course-service",
        path = "/user",
        //url = "http://localhost:8081",
        url = "${user-microservice.url}",
        configuration = FeignConfiguration.class)
public interface UserService {

    @GetMapping("/all")
    List<Object> getAllUsers();

    @GetMapping("/{id}")
    Object getUserByID(@PathVariable Long id);

    @GetMapping("/username/{username}")
    Object getUserByUsername(@PathVariable String username);

    @PutMapping
    Object updateUser(@RequestBody Object requestBody);

    @DeleteMapping("/user/{username}")
    void deleteUserByUsername(@PathVariable String username);

    @PutMapping("/role")
    Object grantRole(@RequestBody Object request);

    @DeleteMapping("/role")
    Object revokeRole(@RequestBody Object request);


}