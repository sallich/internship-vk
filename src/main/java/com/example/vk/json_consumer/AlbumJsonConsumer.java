package com.example.vk.json_consumer;

import com.example.vk.dto.Album;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class AlbumJsonConsumer{
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public List<Album> post(Long userId, List<Album> albums) {
        HttpEntity<List<Album>> request = new HttpEntity<>(albums);
        String postUrl = url + "/users/" + userId + "/albums";
        return Arrays.stream(Objects.requireNonNull(restTemplate.postForObject(postUrl, request, Album[].class))).toList();
    }

    public List<Album> getAlbumsByUserId(Long userId) {
        String getUrl = url + "/users/" + userId + "/albums";
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(getUrl, Album[].class))).toList();
    }

    public List<Album> put(Long userId, List<Album> albums) {
        HttpEntity<List<Album>> httpEntity = new HttpEntity<>(albums);
        String putUrl = url + "/users/" + userId + "/albums";
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(putUrl, HttpMethod.PUT, httpEntity, Album[].class).getBody()));
    }

    public void deleteAlbumsByUserId(Long userId) {
        String delUrl = url + "/users/" + userId + "/albums";
        restTemplate.delete(delUrl);
    }
}
