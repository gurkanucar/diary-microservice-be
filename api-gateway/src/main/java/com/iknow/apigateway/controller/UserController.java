package com.iknow.apigateway.controller;

import com.iknow.apigateway.requestModel.RegisterUserRequest;
import com.iknow.apigateway.requestModel.UpdateUserRequest;
import com.iknow.apigateway.requestService.UserRequestService;
import com.iknow.apigateway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserRequestService userRequestService;
    private final UserService userService;

    public UserController(UserRequestService userRequestService, UserService userService) {
        this.userRequestService = userRequestService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRequestService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userRequestService.getUserByID(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(userRequestService.getUserByUsername(username));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest user) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userRequestService.updateUser(user));
    }

//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest user) {
//        log.info(user.toString());
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(userService.createUser(user));
//    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userRequestService.deleteUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/role")
    public ResponseEntity<?> grantRole(@RequestBody Object request) {
        return ResponseEntity.ok().body(userRequestService.grantRole(request));
    }

    @DeleteMapping("/role")
    public ResponseEntity<?> revokeRole(@RequestBody Object request) {
        return ResponseEntity.ok().body(userRequestService.revokeRole(request));
    }


}
