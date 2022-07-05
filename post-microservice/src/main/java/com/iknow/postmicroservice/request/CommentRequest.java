package com.iknow.postmicroservice.request;

import com.iknow.postmicroservice.model.Comment;
import com.iknow.postmicroservice.model.post.Post;
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