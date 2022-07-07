package com.iknow.apigateway.service;

import com.iknow.apigateway.exception.GeneralException;
import com.iknow.apigateway.model.RefreshToken;
import com.iknow.apigateway.model.User;
import com.iknow.apigateway.repository.RefreshTokenRepository;
import com.iknow.apigateway.requestModel.CreateUserRequest;
import com.iknow.apigateway.requestModel.LoginRequest;
import com.iknow.apigateway.requestModel.RegisterUserRequest;
import com.iknow.apigateway.util.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class AuthService {

    @Value("${jwt-variables.EXPIRES_REFRESH_TOKEN_MINUTE}")
    private String EXPIRES_REFRESH_TOKEN_MINUTE;

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtHelper jwtHelper;

    public AuthService(UserService userService,
                       RefreshTokenRepository refreshTokenRepository,
                       JwtHelper jwtHelper) {
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtHelper = jwtHelper;
    }

    private RefreshToken createRefreshToken(User user) {
        log.info("Refresh token will generate");
        RefreshToken refreshToken = new RefreshToken();
        String token;
        do {

            token = jwtHelper.generateRefreshToken();

        } while (refreshTokenRepository.findByToken(token).isPresent());

        refreshToken.setUsername(user.getUsername());
        refreshToken.setToken(token);
        refreshToken.setExpiryDate(LocalDateTime.now().plusMinutes(Long.parseLong(EXPIRES_REFRESH_TOKEN_MINUTE)));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }


    public Map<String, String> createTokens(User user) {
        return createTokens(user.getUsername());
    }

    public Map<String, String> createTokens(String username) {

        User user = userService.getUserByUsername(username);

        String access_token = jwtHelper.createJwtToken(user);
        log.info("ACCESS TOKEN OLUSTURULDU");
        RefreshToken refreshToken = createRefreshToken(user);

        Map<String, String> map = new HashMap();
        map.put("access_token", access_token);
        map.put("refresh_token", refreshToken.getToken());
        map.put("username", user.getUsername());
        map.put("id", user.getId().toString());
        return map;
    }


    public Map<String, String> registerUser(RegisterUserRequest registerUserRequest) {

       var user = userService.createUser(registerUserRequest);
        return createTokens(user.getUsername());
    }


    public Map<String, String> login(LoginRequest loginRequest) {
        deleteOldTokensByLogin(loginRequest.getUsername());
        var user = userService.getUserByUsername(loginRequest.getUsername());
        log.info("Map Login password: "+loginRequest.getPassword());
        userService.checkLoginUser(loginRequest.getUsername(), loginRequest.getPassword());
        log.info("GECTIIIII");
        return createTokens(user);
    }

    public void logout() {
        deleteOldTokensByUsername(getAuthenticatedUser().getUsername());
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(auth.getName());
    }

    public UsernamePasswordAuthenticationToken getAuthToken(String token) {
        return jwtHelper.getAuthToken(token);
    }


    public Map<String, String> refreshTokens(String refreshToken) {

        RefreshToken token = getRefreshToken(refreshToken);

        if (token != null) {
            deleteRefreshToken(token.getToken());

            return createTokens(token.getUsername());
        } else
            throw new GeneralException("Invalid refresh token!", HttpStatus.BAD_REQUEST);

    }

    public void deleteRefreshToken(String token) {
        RefreshToken refreshToken = getRefreshToken(token);
        refreshTokenRepository.delete(refreshToken);
    }

    public void deleteOldTokensByLogin(String username) {
        refreshTokenRepository.deleteRefreshTokenByUsername(username);
    }

    public void deleteOldTokensByUsername(String username) {

        if (refreshTokenRepository.findByUsername(username).isPresent()) {
            refreshTokenRepository.deleteRefreshTokenByUsername(username);
        } else {
            throw new GeneralException("you already have been logouted!", HttpStatus.BAD_REQUEST);
        }

    }


    protected RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new GeneralException("token not found", HttpStatus.NOT_FOUND));
    }


}
