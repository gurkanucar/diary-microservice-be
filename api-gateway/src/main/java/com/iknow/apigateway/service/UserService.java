package com.iknow.apigateway.service;


import com.iknow.apigateway.exception.GeneralException;
import com.iknow.apigateway.model.ROLES;
import com.iknow.apigateway.model.Role;
import com.iknow.apigateway.model.User;
import com.iknow.apigateway.requestModel.RegisterUserRequest;
import com.iknow.apigateway.requestModel.UpdateUserRequest;
import com.iknow.apigateway.requestService.UserRequestService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRequestService userRequestService;
    private final ModelMapper modelMapper;

    public UserService(PasswordEncoder passwordEncoder, RoleService roleService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRequestService userRequestService,
                       ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRequestService = userRequestService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByUsername(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<User> getAllUsers() {
        return userRequestService.getAllUsers().stream().map(x -> modelMapper.map(x, User.class)).collect(Collectors.toList());
    }

    public User getMyself() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getUserByUsername(auth.getName());
    }

    public User getUserByPermit(String username) {
        User user = getUserByUsername(username);
        if (!isAuthorized(user)) {
            throw new GeneralException("you can not make this operation!", HttpStatus.UNAUTHORIZED);
        }
        return user;
    }


    public void updateUser(User user) {
        User existing = getUserByUsername(user.getUsername());

        // DON'T UPDATE PASSWORD HERE!!
        // TODO create update password method
        //  existing.setPassword(passwordEncoder.encode(user.getPassword()));
        existing.setEmail(user.getEmail());
        existing.setName(user.getName());
        existing.setSurname(user.getSurname());
        if (doesIncludesRoles(List.of(ROLES.ADMIN, ROLES.SUPERADMIN), user.getRoles())) {
            existing.setRoles(user.getRoles());
        }
        userRequestService.updateUser(modelMapper.map(existing, UpdateUserRequest.class));
    }


    public void deleteUser(String username) {
        // get the user if it is admin or himself
        User existing = getUserByPermit(username);
        userRequestService.deleteUserByUsername(existing.getUsername());
    }


    protected User getUserByUsername(String username) {
        return userRequestService.getUserByUsername(username);
    }

    protected User checkLoginUser(String username, String password) {
        var user = getUserByUsername(username);
        log.info("login attempt user: " + user.toString());
        log.info("login attempt user password: " + user.getPassword() + "   request password: " + password);
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new GeneralException("wrong password!", HttpStatus.NOT_FOUND);
        }

        return user;
    }


    private boolean isAuthorized(User unknownUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByUsername(auth.getName());

        var isAdmin = doesIncludesRoles(List.of(ROLES.SUPERADMIN, ROLES.ADMIN), user.getRoles());

        var isOwner = unknownUser.getId().equals(user.getId());

        return isAdmin || isOwner;
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


    protected boolean doesIncludesRoles(List<ROLES> checkRoles, List<Role> roles) {
        return roles.stream().anyMatch(role -> checkRoles.stream().anyMatch(x -> x.toString().equals(role.getName())));
    }

    public User createUser(RegisterUserRequest registerUserRequest) {
        var user = userRequestService.createUser(registerUserRequest);

        return user;

    }
}
