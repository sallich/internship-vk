package com.example.vk.json_consumer;

import com.example.vk.dto.UsersToDo;
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
public class UsersToDoJsonConsumer {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public UsersToDo post(Long userId, UsersToDo usersToDo){
        HttpEntity<UsersToDo> request = new HttpEntity<>(usersToDo);
        return restTemplate.postForObject(url+"/users/"+userId+"/todos", request, UsersToDo.class);
    }

    public Set<UsersToDo> getToDoByUserId(Long userId) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url+"/users/"+userId+"/todos", UsersToDo[].class)))
                .collect(Collectors.toSet());
    }

    public UsersToDo put(Long id, UsersToDo usersToDo) {
        HttpEntity<UsersToDo> httpEntity = new HttpEntity<>(usersToDo);
        return restTemplate.exchange(url + "/users/"+id+"/todos", HttpMethod.PUT, httpEntity, UsersToDo.class).getBody();
    }

    public void deleteTodoByUserId(Long userId){
        String newUrl = url+"/users/"+userId+"/todos";
        restTemplate.delete(newUrl);
    }
}
