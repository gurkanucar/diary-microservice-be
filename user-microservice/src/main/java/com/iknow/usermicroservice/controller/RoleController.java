package com.iknow.usermicroservice.controller;

import com.iknow.usermicroservice.dto.RoleDTO;
import com.iknow.usermicroservice.model.Role;
import com.iknow.usermicroservice.request.RoleRequest;
import com.iknow.usermicroservice.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final ModelMapper mapper;

    public RoleController(RoleService roleService,
                          ModelMapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> saveRole(@Valid @RequestBody RoleRequest roleRequest) {
        var role = mapper.map(roleRequest, Role.class);
        return ResponseEntity.status(201).body(roleService.saveRole(role));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getRoles() {
        var list = roleService.getRoles()
                .stream().map(x -> mapper.map(x, RoleDTO.class));
        return ResponseEntity.ok(list);
    }




}
