package com.example.vk.json_consumer;

import com.example.vk.dto.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * Похож на PostJsonConsumer, но предназначен для работы с всеми альбомами пользователя
 */
@Controller
public class PostsJsonConsumer{
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public List<Post> post(Long userId, List<Post> posts) {
        HttpEntity<List<Post>> request = new HttpEntity<>(posts);
        String postUrl = url + "/users/" + userId + "/posts";
        return List.of(Objects.requireNonNull(restTemplate.postForObject(postUrl, request, Post[].class)));
    }

    public List<Post> getPostsByUserId(Long userId) {
        String getUrl = url + "/users/" + userId + "/posts";
        return List.of(Objects.requireNonNull(restTemplate.getForObject(getUrl, Post[].class)));
    }

    public List<Post> put(Long id, List<Post> posts) {
        HttpEntity<List<Post>> httpEntity = new HttpEntity<>(posts);
        String putUrl = url + "/users/" + id + "/posts";
        return List.of(Objects.requireNonNull(restTemplate
                .exchange(putUrl, HttpMethod.PUT, httpEntity, Post[].class).getBody()));
    }

    public void deletePostsByUserId(Long userId) {
        String newUrl = url + "/users/" + userId + "/posts";
        restTemplate.delete(newUrl);
    }
}
