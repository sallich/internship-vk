package com.example.vk.services;

import com.example.vk.json_consumer.UsersPostsJsonConsumer;
import com.example.vk.dto.UsersPosts;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsersPostsService {
    private final UsersPostsJsonConsumer usersPostsJsonConsumer;

    public UsersPosts savePosts(Long userId, UsersPosts usersPosts){
        return usersPostsJsonConsumer.post(userId,usersPosts);
    }

    @Cacheable("usersPosts")
    public Set<UsersPosts> getPostsByUserId(Long userId){
        return usersPostsJsonConsumer.getPostsByUserId(userId);
    }

    @CachePut(value = "usersPosts", key = "usersPosts.userId")
    public UsersPosts put(Long id, UsersPosts usersPosts){
        return usersPostsJsonConsumer.put(id,usersPosts);
    }

    @CacheEvict("usersPosts")
    public void deletePostsByUserId(Long userId){
        usersPostsJsonConsumer.deletePostsByUserId(userId);
    }
}
