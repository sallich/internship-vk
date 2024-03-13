package com.example.vk.services.proxy;

import com.example.vk.dto.Todo;
import com.example.vk.json_consumer.TodoJsonConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TodoService{
    private final TodoJsonConsumer todoJsonConsumer;

    @Cacheable("todo")
    public List<Todo> post(Long userId, List<Todo> todos) {
        return todoJsonConsumer.post(userId, todos);
    }

    @Cacheable("todo")
    public List<Todo> getToDoByUserId(Long userId) {
        return todoJsonConsumer.getToDoByUserId(userId);
    }

    @CachePut(value = "todo", key = "userId")
    public List<Todo> put(Long userId, List<Todo> todos) {
        return todoJsonConsumer.put(userId, todos);
    }

    @CacheEvict("todo")
    public void deleteTodoByUserId(Long userId) {
        todoJsonConsumer.deleteTodoByUserId(userId);
    }

}
