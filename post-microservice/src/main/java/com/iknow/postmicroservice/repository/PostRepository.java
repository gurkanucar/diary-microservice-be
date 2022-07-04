package com.iknow.postmicroservice.repository;

import com.iknow.postmicroservice.model.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository< Post, String> {

    @Override
    <S extends Post> S save(S entity);
}
