package com.iknow.postmicroservice.request;


import com.iknow.postmicroservice.model.User;
import com.iknow.postmicroservice.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequest {

    private Post post;
    private User user;

}
