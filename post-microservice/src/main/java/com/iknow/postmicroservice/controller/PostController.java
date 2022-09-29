package com.iknow.postmicroservice.controller;

import com.iknow.postmicroservice.model.post.*;
import com.iknow.postmicroservice.request.CommentRequest;
import com.iknow.postmicroservice.request.LikeRequest;
import com.iknow.postmicroservice.service.PostCacheService;
import com.iknow.postmicroservice.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostCacheService postCacheService;


    public PostController(PostService postService, PostCacheService postCacheService) {
        this.postService = postService;
        this.postCacheService = postCacheService;
    }


    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{postID}")
    public ResponseEntity<Post> getPostById(@PathVariable String postID) {
        return ResponseEntity.ok(postService.getPostByID(postID));
    }

    @DeleteMapping("/{postID}")
    public ResponseEntity<?> deletePostByID(@PathVariable String postID) {
        postService.deletePostByID(postID);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.create(post));
    }

    @PostMapping("/comment")
    public ResponseEntity<Post> addComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(postService.addComment(commentRequest));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Post> removeComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(postService.removeComment(commentRequest));
    }

    @PostMapping("/like")
    public ResponseEntity<Post> addLike(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(postService.addLike(likeRequest));
    }

    @DeleteMapping("/like")
    public ResponseEntity<Post> removeLike(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(postService.removeLike(likeRequest));
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Post> changeStatus(@PathVariable String id) {
        return ResponseEntity.ok(postService.changeStatus(id));
    }


    @GetMapping("/cache/get-all")
    public ResponseEntity<List<PostCache>> getAll() {
        return ResponseEntity.ok(postCacheService.getAll());
    }

    @PostMapping("/cache")
    public ResponseEntity<PostCache> createPostCache(@RequestBody PostCache postCache) {
        return ResponseEntity.ok(postCacheService.create(postCache));
    }


}
