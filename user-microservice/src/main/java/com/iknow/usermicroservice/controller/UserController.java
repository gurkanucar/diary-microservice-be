package com.iknow.usermicroservice.controller;

import com.iknow.usermicroservice.dto.UserDTO;
import com.iknow.usermicroservice.model.User;
import com.iknow.usermicroservice.request.RoleGrantRevokeRequest;
import com.iknow.usermicroservice.request.UpdateUserRequest;
import com.iknow.usermicroservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.mapper = modelMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        //convert to dto
        var list = userService.getAllUsers()
                .stream().map(x -> mapper.map(x, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.map(userService.getUserById(id), UserDTO.class));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(mapper.map(userService.getUserByUsername(username), UserDTO.class));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest user) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUser(mapper.map(user, User.class)));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/role")
    public ResponseEntity<?> grantRole(@Valid @RequestBody RoleGrantRevokeRequest request) {
        var dto = mapper.map(userService.grantRole(request.getUsername(), request.getRole()), UserDTO.class);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/role")
    public ResponseEntity<?> revokeRole(@Valid @RequestBody RoleGrantRevokeRequest request) {
        var dto = mapper.map(userService.revokeRole(request.getUsername(), request.getRole()), UserDTO.class);
        return ResponseEntity.ok().body(dto);
    }


}
