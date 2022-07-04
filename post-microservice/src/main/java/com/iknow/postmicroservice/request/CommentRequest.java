package com.iknow.postmicroservice.request;

import com.iknow.postmicroservice.model.Comment;
import com.iknow.postmicroservice.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {

    private Post post;
    private Comment comment;

}