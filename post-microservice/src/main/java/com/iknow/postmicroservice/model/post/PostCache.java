package com.iknow.postmicroservice.model.post;

import com.iknow.postmicroservice.model.Comment;
import com.iknow.postmicroservice.model.LikeModel;
import com.iknow.postmicroservice.model.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("PostCache")
public class PostCache implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private User user;
    private List<LikeModel> likes;
    private List<Comment> comments;
    private LocalDateTime created;
    private String imageUrl;
    private boolean isPublic;
}
