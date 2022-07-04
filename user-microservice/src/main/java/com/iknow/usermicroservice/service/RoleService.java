package com.iknow.usermicroservice.service;

import com.iknow.usermicroservice.exception.GeneralException;
import com.iknow.usermicroservice.model.Role;
import com.iknow.usermicroservice.repository.RoleRepository;
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
