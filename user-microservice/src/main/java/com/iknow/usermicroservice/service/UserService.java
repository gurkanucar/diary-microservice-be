package com.iknow.usermicroservice.service;

import com.iknow.usermicroservice.exception.GeneralException;
import com.iknow.usermicroservice.model.ROLES;
import com.iknow.usermicroservice.model.Role;
import com.iknow.usermicroservice.model.User;
import com.iknow.usermicroservice.repository.UserRepository;
import com.iknow.usermicroservice.request.RegisterUserRequest;
import com.iknow.usermicroservice.request.UpdateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, ModelMapper mapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.roleService = roleService;
    }


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new GeneralException("User not found by id!", 404));
    }


    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new GeneralException("User not found by id!", 404));
    }

    public User registerUser(RegisterUserRequest userRequest) {
        if (userRepository.findUserByUsername(userRequest.getUsername()).isPresent()) {
            throw new GeneralException("User already exists", HttpStatus.CONFLICT);
        }

        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
            throw new GeneralException("email exists", HttpStatus.CONFLICT);
        }

        User user = mapper.map(userRequest, User.class);
        user.getRoles().add(roleService.getRoleByName("USER"));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User updateUser(User user) {
        var existingUser = getUserByUsername(user.getUsername());
        existingUser.setBiography(user.getBiography());
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setProfileImageUrl(user.getProfileImageUrl());
        return userRepository.save(existingUser);
    }

    public void deleteUser(String username) {
        var existingUser = getUserByUsername(username);
        userRepository.delete(existingUser);
    }


    public User grantRole(String username, String roleName) {
        User user = getUserByUsername(username);
        Role role = roleService.getRoleByName(roleName);
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            updateUser(user);
        } else {
            throw new GeneralException("Role already exists!", HttpStatus.NOT_ACCEPTABLE);
        }

        return user;
    }

    public User revokeRole(String username, String roleName) {
        User user = getUserByUsername(username);
        Role role = roleService.getRoleByName(roleName);
        var roles = user.getRoles();
        if (roles.size() > 1 && !role.getName().equals(ROLES.SUPERADMIN.toString())) {
            user.getRoles().remove(role);
            updateUser(user);
        } else if (role.getName().equals(ROLES.SUPERADMIN.toString())) {
            throw new GeneralException("Are you kidding :D", HttpStatus.NOT_ACCEPTABLE);
        } else {
            throw new GeneralException("Every user must have a role at least!", HttpStatus.NOT_ACCEPTABLE);
        }
        return user;
    }
}
