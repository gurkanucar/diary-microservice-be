package com.iknow.postmicroservice.repository;

import com.iknow.postmicroservice.model.post.PostCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCacheRepository extends CrudRepository<PostCache, Long> {

}
