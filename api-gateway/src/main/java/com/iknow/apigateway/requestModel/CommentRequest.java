package com.iknow.apigateway.requestModel;

import com.iknow.apigateway.model.Comment;
import com.iknow.apigateway.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {

    private Post post;
    private Comment comment;

}