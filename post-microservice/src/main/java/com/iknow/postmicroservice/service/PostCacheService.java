package com.iknow.postmicroservice.service;

import com.iknow.postmicroservice.model.post.PostCache;
import com.iknow.postmicroservice.repository.PostCacheRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCacheService {

    private final PostCacheRepository postCacheRepository;


    public PostCacheService(PostCacheRepository postCacheRepository) {
        this.postCacheRepository = postCacheRepository;
    }

    public List<PostCache> getAll() {
        return (List<PostCache>) postCacheRepository.findAll();
    }

    public PostCache getByID(Long id) {
        return postCacheRepository
                .findById(id).orElseThrow(() -> new RuntimeException("not found!"));
    }

    public PostCache create(PostCache postCache) {
        return postCacheRepository.save(postCache);
    }

    public PostCache update(Long id, PostCache postCache) {
        PostCache old = getByID(id);
        old.setContent(postCache.getContent());
        return postCacheRepository.save(old);
    }

    public void deleteByID(Long id) {
        PostCache postCache = getByID(id);
        postCacheRepository.delete(postCache);
    }

}
