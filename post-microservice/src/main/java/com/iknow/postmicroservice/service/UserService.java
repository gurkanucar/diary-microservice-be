package com.iknow.postmicroservice.service;


import com.iknow.postmicroservice.model.LikeModel;
import com.iknow.postmicroservice.model.User;
import com.iknow.postmicroservice.requestService.UserRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRequestService userRequestService;

    public UserService(UserRequestService userRequestService) {
        this.userRequestService = userRequestService;
    }

    public User getUserByUsername(String username) {
        return userRequestService.getUserByUsername(username);
        //.orElseThrow(() -> new RuntimeException("user not found!"));
    }

    public User getUserById(Long id) {
        return userRequestService.getUserByID(id);
        // .orElseThrow(() -> new RuntimeException("user not found!"));
    }

    protected boolean isUserContains(final List<LikeModel> list, final String username) {
        return list.stream().anyMatch(o -> o.getUser().getUsername().equals(username));
    }


}
