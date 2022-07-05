package com.iknow.postmicroservice.repository;

import com.iknow.postmicroservice.model.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findPostById(String id);

}
