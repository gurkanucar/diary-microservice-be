package com.iknow.apigateway.service;


import com.iknow.apigateway.exception.GeneralException;
import com.iknow.apigateway.model.Role;
import com.iknow.apigateway.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name)
                .orElseThrow(() -> new GeneralException("Role not found!", HttpStatus.NOT_FOUND));
    }


    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }



}
