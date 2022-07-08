package com.iknow.apigateway.requestService;


import com.iknow.apigateway.model.post.Post;
import com.iknow.apigateway.requestModel.CommentRequest;
import com.iknow.apigateway.requestModel.LikeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "post-microservice",
        path = "/post",
        configuration = FeignConfiguration.class)
public interface PostRequestService {


    @GetMapping
    List<Post> getAllPosts();

    @GetMapping("/{postID}")
    Post getPostById(@PathVariable String postID);

    @DeleteMapping("/{postID}")
    void deletePostByID(@PathVariable String postID);

    @PostMapping
    Post createPost(@RequestBody Post post);

    @PostMapping("/comment")
    Post addComment(@RequestBody CommentRequest commentRequest);

    @DeleteMapping("/comment")
    Post removeComment(@RequestBody CommentRequest commentRequest);

    @PostMapping("/like")
    Post addLike(@RequestBody LikeRequest likeRequest);

    @DeleteMapping("/like")
    Post removeLike(@RequestBody LikeRequest likeRequest);


}