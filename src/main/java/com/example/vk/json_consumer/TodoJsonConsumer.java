package com.example.vk.json_consumer;

import com.example.vk.dto.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Controller
public class TodoJsonConsumer{
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public List<Todo> post(Long userId, List<Todo> todos) {
        HttpEntity<List<Todo>> request = new HttpEntity<>(todos);
        String postUrl = url + "/users/" + userId + "/todos";
        return List.of(Objects.requireNonNull(restTemplate.postForObject(postUrl, request, Todo.class)));
    }

    public List<Todo> getToDoByUserId(Long userId) {
        String getUrl = url + "/users/" + userId + "/todos";
        return List.of(Objects.requireNonNull(restTemplate.getForObject(getUrl, Todo[].class)));
    }

    public List<Todo> put(Long id, List<Todo> todos) {
        HttpEntity<List<Todo>> httpEntity = new HttpEntity<>(todos);
        String putUrl = url + "/users/" + id + "/todos";
        return List.of(Objects.requireNonNull(restTemplate.exchange(putUrl, HttpMethod.PUT, httpEntity, Todo[].class).getBody()));
    }

    public void deleteTodoByUserId(Long userId) {
        String newUrl = url + "/users/" + userId + "/todos";
        restTemplate.delete(newUrl);
    }
}
