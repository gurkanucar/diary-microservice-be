package com.iknow.apigateway.controller;

import com.iknow.apigateway.requestModel.CreateUserRequest;
import com.iknow.apigateway.requestModel.LoginRequest;
import com.iknow.apigateway.requestModel.RefreshTokenRequest;
import com.iknow.apigateway.requestModel.RegisterUserRequest;
import com.iknow.apigateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok().body(authService.registerUser(registerUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.info(loginRequest.toString());
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok().body(authService.refreshTokens(refreshTokenRequest.getToken()));
    }


}
