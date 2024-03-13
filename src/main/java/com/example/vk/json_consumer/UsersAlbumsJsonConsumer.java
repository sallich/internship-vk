package com.example.vk.json_consumer;

import com.example.vk.dto.UsersAlbums;
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
public class UsersAlbumsJsonConsumer {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public UsersAlbums post(Long userId, UsersAlbums usersAlbums){
        HttpEntity<UsersAlbums> request = new HttpEntity<>(usersAlbums);
        return restTemplate.postForObject(url+"/users/"+userId+"/albums", request, UsersAlbums.class);
    }

    public Set<UsersAlbums> getAlbumsByUserId(Long userId) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url+"/users/"+userId+"/albums", UsersAlbums[].class)))
                .collect(Collectors.toSet());
    }

    public UsersAlbums put(Long id, UsersAlbums usersAlbums) {
        HttpEntity<UsersAlbums> httpEntity = new HttpEntity<>(usersAlbums);
        return restTemplate.exchange(url + "/users/"+id+"/albums", HttpMethod.PUT, httpEntity, UsersAlbums.class).getBody();
    }

    public void deleteAlbumsByUserId(Long userId){
        String newUrl = url+"/users/"+userId+"/albums";
        restTemplate.delete(newUrl);
    }
}
