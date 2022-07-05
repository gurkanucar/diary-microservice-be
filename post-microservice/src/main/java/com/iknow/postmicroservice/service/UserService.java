package com.iknow.postmicroservice.service;


import com.iknow.postmicroservice.model.LikeModel;
import com.iknow.postmicroservice.model.User;
import com.iknow.postmicroservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found!"));
    }

    public User getUserById(String id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("user not found!"));
    }

    protected boolean isUserContains(final List<LikeModel> list, final String username) {
        return list.stream().anyMatch(o -> o.getUser().getUsername().equals(username));
    }


}
