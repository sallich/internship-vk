package com.example.vk.json_consumer;

import com.example.vk.dto.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CommentsJsonConsumer {
    private final RestTemplate restTemplate = new RestTemplate();


    @Value("${json-source.url}")
    private String url;

    public Comments post(Long postId, Comments comments) {
        HttpEntity<Comments> request = new HttpEntity<>(comments);
        return restTemplate.postForObject(url + "/posts/" + postId + "/comments", request, Comments.class);
    }

    public Set<Comments> getByPostId(Long postId) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url + "/posts/" + postId + "/comments", Comments[].class)))
                .collect(Collectors.toSet());
    }

    public void deleteByPostId(Long postId) {
        String newUrl = url + "/posts/" + postId + "/comments";
        restTemplate.delete(newUrl);
    }

    public Comments put(Long postId, Comments comments) {
        HttpEntity<Comments> httpEntity = new HttpEntity<>(comments);
        return restTemplate.exchange(url + "/posts/" + postId + "/comments", HttpMethod.PUT, httpEntity, Comments.class).getBody();
    }

}
