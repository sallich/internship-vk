package com.example.vk.json_consumer;

import com.example.vk.dto.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Controller
public class CommentsJsonConsumer{
    private final RestTemplate restTemplate = new RestTemplate();


    @Value("${json-source.url}")
    private String url;

    public List<Comment> post(Long postId, List<Comment> comments) {
        HttpEntity<List<Comment>> request = new HttpEntity<>(comments);
        String postUrl = url + "/posts/" + postId + "/comments";
        return List.of(Objects.requireNonNull(restTemplate.postForObject(postUrl, request, Comment[].class)));
    }

    public List<Comment> getByPostId(Long postId) {
        String getUrl = url + "/posts/" + postId + "/comments";
        return List.of(Objects.requireNonNull(restTemplate.getForObject(getUrl, Comment[].class)));
    }

    public void deleteByPostId(Long postId) {
        String newUrl = url + "/posts/" + postId + "/comments";
        restTemplate.delete(newUrl);
    }

    public List<Comment> put(Long postId, List<Comment> comments) {
        HttpEntity<List<Comment>> httpEntity = new HttpEntity<>(comments);
        String putUrl = url + "/posts/" + postId + "/comments";
        return List.of(Objects.requireNonNull(restTemplate
                .exchange(putUrl, HttpMethod.PUT, httpEntity, Comment[].class).getBody()));
    }

}
