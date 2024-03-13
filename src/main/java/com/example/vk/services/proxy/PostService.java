package com.example.vk.services.proxy;

import com.example.vk.json_consumer.PostJsonConsumer;
import com.example.vk.dto.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJsonConsumer postJsonConsumer;

    @Cacheable("post")
    public Post getById(Long id){
        return postJsonConsumer.getById(id);
    }

    public Set<Post> getAllPosts(){
        return postJsonConsumer.getAllPosts();
    }

    @Cacheable("post")
    public Post post(Post post){
        return postJsonConsumer.post(post);
    }

    @CachePut(value = "post", key = "postId")
    public Post put(Long postId, Post post){
        return postJsonConsumer.put(postId,post);
    }

    @CacheEvict("post")
    public void delete(Long id){
        postJsonConsumer.delete(id);
    }
}
