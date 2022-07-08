package com.iknow.apigateway.requestModel;


import com.iknow.postmicroservice.model.User;
import com.iknow.postmicroservice.model.post.PostType;
import lombok.Data;

@Data
public class CreatePostRequest {

    private String content;
    private User user;
    private PostType type;
    private String imageUrl;
    private String videoUrl;

}
