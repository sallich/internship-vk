package com.example.vk.services;

import com.example.vk.json_consumer.UsersToDoJsonConsumer;
import com.example.vk.dto.UsersToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsersToDoService {
    private final UsersToDoJsonConsumer usersToDoJsonConsumer;

    public UsersToDo post(Long userId, UsersToDo usersToDo){
        return usersToDoJsonConsumer.post(userId,usersToDo);
    }

    @Cacheable("todoByUserId")
    public Set<UsersToDo> getToDoByUserId(Long userId) {
        return usersToDoJsonConsumer.getToDoByUserId(userId);
    }


    @CachePut(value = "todoByUserId", key = "usersToDo.userId")
    public UsersToDo put(Long id, UsersToDo usersToDo){
        return usersToDoJsonConsumer.put(id,usersToDo);
    }

    @CacheEvict("todoByUserId")
    public void deleteTodoByUserId(Long userId){
        usersToDoJsonConsumer.deleteTodoByUserId(userId);
    }



}
