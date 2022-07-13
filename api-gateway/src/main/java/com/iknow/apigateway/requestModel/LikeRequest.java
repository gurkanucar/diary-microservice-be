package com.iknow.apigateway.requestModel;


import com.iknow.apigateway.model.LikeModel;
import com.iknow.apigateway.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequest {

    private Post post;
    private LikeModel likeModel;

}
