package com.iknow.apigateway.controller;

import com.iknow.apigateway.requestService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByID(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Object user) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/role")
    public ResponseEntity<?> grantRole(@RequestBody Object request) {
        return ResponseEntity.ok().body(userService.grantRole(request));
    }

    @DeleteMapping("/role")
    public ResponseEntity<?> revokeRole(@RequestBody Object request) {
        return ResponseEntity.ok().body(userService.revokeRole(request));
    }


}
