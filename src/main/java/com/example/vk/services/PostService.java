package com.example.vk.services;

import com.example.vk.json_consumer.PostsJsonConsumer;
import com.example.vk.dto.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostsJsonConsumer postsJsonConsumer;

    @Cacheable("post")
    public Post getById(Long id){
        return postsJsonConsumer.getById(id);
    }

    public Set<Post> getAllPosts(){
        return postsJsonConsumer.getAllPosts();
    }

    @Cacheable("post")
    public Post post(Post post){
        return postsJsonConsumer.post(post);
    }

    @CachePut(value = "post", key = "postId")
    public Post put(Long postId, Post post){
        return postsJsonConsumer.put(postId,post);
    }

    @CacheEvict("post")
    public void delete(Long id){
        postsJsonConsumer.delete(id);
    }
}
