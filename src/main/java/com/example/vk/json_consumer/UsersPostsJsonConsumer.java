package com.example.vk.json_consumer;

import com.example.vk.dto.UsersPosts;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UsersPostsJsonConsumer {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public UsersPosts post(Long userId, UsersPosts usersPosts){
        HttpEntity<UsersPosts> request = new HttpEntity<>(usersPosts);
        return restTemplate.postForObject(url+"/users/"+userId+"/posts", request, UsersPosts.class);
    }

    public Set<UsersPosts> getPostsByUserId(Long userId) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url+"/users/"+userId+"/posts", UsersPosts[].class)))
                .collect(Collectors.toSet());
    }

    public UsersPosts put(Long id, UsersPosts usersPosts) {
        HttpEntity<UsersPosts> httpEntity = new HttpEntity<>(usersPosts);
        return restTemplate.exchange(url + "/users/"+id+"/posts", HttpMethod.PUT, httpEntity, UsersPosts.class).getBody();
    }

    public void deletePostsByUserId(Long userId){
        String newUrl = url+"/users/"+userId+"/posts";
        restTemplate.delete(newUrl);
    }
}
