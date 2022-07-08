package com.iknow.postmicroservice.requestService;


import com.iknow.postmicroservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user-microservice",
        path = "/user",
        //url = "http://localhost:8081",
        //  url = "${user-microservice.url}",
        configuration = FeignConfiguration.class)
public interface UserRequestService {

    @GetMapping("/all")
    List<User> getAllUsers();

    @GetMapping("/{id}")
    User getUserByID(@PathVariable Long id);

    @GetMapping("/username/{username}")
    User getUserByUsername(@PathVariable String username);


}