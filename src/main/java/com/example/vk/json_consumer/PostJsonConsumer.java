package com.example.vk.json_consumer;

import com.example.vk.dto.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class PostJsonConsumer{
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public Post getById(Long id) {
        return restTemplate.getForObject(url + "/posts/" + id, Post.class, id);
    }

    public Set<Post> getAllPosts() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url+ "/posts", Post[].class)))
                .collect(Collectors.toSet());
    }

    public Post post(Post post) {
        HttpEntity<Post> request = new HttpEntity<>(post);
        return restTemplate.postForObject(url + "/posts" , request, Post.class);
    }

    public Post put(Long postId, Post post) {
        HttpEntity<Post> httpEntity = new HttpEntity<>(post);
        return restTemplate.exchange(url + "/posts/" + postId, HttpMethod.PUT, httpEntity, Post.class).getBody();
    }
    public void delete(Long id) {
        String newUrl = url + "/posts/" + id;
        restTemplate.delete(newUrl);
    }

}
