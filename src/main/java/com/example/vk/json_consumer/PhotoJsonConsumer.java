package com.example.vk.json_consumer;

import com.example.vk.dto.Photo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Controller
public class PhotoJsonConsumer{
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public List<Photo> postByAlbumId(Long albumId, List<Photo> photos) {
        HttpEntity<List<Photo>> request = new HttpEntity<>(photos);
        return List.of(Objects.requireNonNull(restTemplate
                .postForObject(url + "/albums/" + albumId + "/photos", request, Photo[].class)));
    }


    public List<Photo> getByAlbumId(Long albumId) {
        String getUrl = url + "/albums/" + albumId + "/photos";
        return List.of(Objects.requireNonNull(restTemplate.getForObject(getUrl, Photo[].class)));
    }

    public List<Photo> put(Long id, List<Photo> photos) {
        HttpEntity<List<Photo>> httpEntity = new HttpEntity<>(photos);
        String photosUrl = url + "/albums/" + id + "/photos";
        return List.of(Objects.requireNonNull(restTemplate
                .exchange(photosUrl, HttpMethod.PUT, httpEntity, Photo[].class).getBody()));
    }

    public void deleteByAlbumId(Long albumId) {
        String newUrl = url + "/albums/" + albumId + "/photos";
        restTemplate.delete(newUrl);
    }
}
