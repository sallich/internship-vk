package com.example.vk.json_consumer;

import com.example.vk.dto.Album;
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
public class AlbumsJsonConsumer {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${json-source.url}")
    private String url;

    public Album postByAlbumId(Long albumId, Album album){
        HttpEntity<Album> request = new HttpEntity<>(album);
        return restTemplate.postForObject(url+"/albums/"+albumId+"/photos", request, Album.class);
    }



    public Set<Album> getByAlbumId(Long albumId) {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url+"/albums/"+albumId+"/photos", Album[].class)))
                .collect(Collectors.toSet());
    }

    public Album put(Long id, Album album) {
        HttpEntity<Album> httpEntity = new HttpEntity<>(album);
        String photosUrl = url + "/albums/" + id + "/photos";
        return restTemplate.exchange(photosUrl, HttpMethod.PUT, httpEntity, Album.class).getBody();
    }

    public void deleteByAlbumId(Long albumId){
        String newUrl = url+"/albums/"+albumId+"/photos";
        restTemplate.delete(newUrl);
    }
}
