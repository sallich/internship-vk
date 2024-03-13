package com.example.vk.services.proxy;

import com.example.vk.dto.Post;
import com.example.vk.json_consumer.PostsJsonConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Похож на PostService, но предназначен для работы с всеми альбомами пользователя
 */
@Service
@RequiredArgsConstructor
public class PostsService{
    private final PostsJsonConsumer postsJsonConsumer;

    @Cacheable(value = "posts", key = "userId")
    public List<Post> savePosts(Long userId, List<Post> posts) {
        return postsJsonConsumer.post(userId, posts);
    }

    @Cacheable("posts")
    public List<Post> getPostsByUserId(Long userId) {
        return postsJsonConsumer.getPostsByUserId(userId);
    }

    @CachePut(value = "posts", key = "userId")
    public List<Post> put(Long userId, List<Post> posts) {
        return postsJsonConsumer.put(userId, posts);
    }

    @CacheEvict("posts")
    public void deletePostsByUserId(Long userId) {
        postsJsonConsumer.deletePostsByUserId(userId);
    }
}
