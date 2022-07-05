package com.iknow.postmicroservice.model.post;

import com.iknow.postmicroservice.model.Comment;
import com.iknow.postmicroservice.model.LikeModel;
import com.iknow.postmicroservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Post {
    @Id
    private String id;
    private String content;
    private User user;
    private List<LikeModel> likes;
    private List<Comment> comments;
    private LocalDateTime created;
}
