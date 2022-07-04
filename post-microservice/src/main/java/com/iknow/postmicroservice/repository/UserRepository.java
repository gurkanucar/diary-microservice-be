package com.iknow.postmicroservice.repository;

import com.iknow.postmicroservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(String id);
}
