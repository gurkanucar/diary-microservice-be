package com.iknow.apigateway.model.post;

import com.iknow.apigateway.model.Comment;
import com.iknow.apigateway.model.LikeModel;
import com.iknow.apigateway.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    private String id;
    private String content;
    private User user;
    private List<LikeModel> likes;
    private List<Comment> comments;
    private LocalDateTime created;
    private String imageUrl;
}
