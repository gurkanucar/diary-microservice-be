package com.iknow.apigateway.repository;


import com.iknow.apigateway.model.RefreshToken;
import com.iknow.apigateway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUsername(String user);

    void deleteRefreshTokenByUsername(String user);

}
