package com.iknow.apigateway.controller;

import com.iknow.apigateway.model.post.Post;
import com.iknow.apigateway.requestModel.CommentRequest;
import com.iknow.apigateway.requestModel.LikeRequest;
import com.iknow.apigateway.requestService.PostRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    private final PostRequestService postRequestService;


    public PostController(PostRequestService postRequestService) {
        this.postRequestService = postRequestService;
    }


    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postRequestService.getAllPosts());
    }

    @GetMapping("/{postID}")
    public ResponseEntity<Post> getPostById(@PathVariable String postID) {
        return ResponseEntity.ok(postRequestService.getPostById(postID));
    }

    @DeleteMapping("/{postID}")
    public ResponseEntity<?> deletePostByID(@PathVariable String postID) {
        postRequestService.deletePostByID(postID);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postRequestService.createPost(post));
    }

    @PostMapping("/comment")
    public ResponseEntity<Post> addComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(postRequestService.addComment(commentRequest));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Post> removeComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(postRequestService.removeComment(commentRequest));
    }

    @PostMapping("/like")
    public ResponseEntity<Post> addLike(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(postRequestService.addLike(likeRequest));
    }

    @DeleteMapping("/like")
    public ResponseEntity<Post> removeLike(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(postRequestService.removeLike(likeRequest));
    }


}
