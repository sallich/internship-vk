package com.example.vk.services.proxy;

import com.example.vk.dto.Album;
import com.example.vk.json_consumer.AlbumJsonConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlbumService{

    private final AlbumJsonConsumer albumJsonConsumer;

    public List<Album> saveAlbums(Long userId, List<Album> albums) {
        return albumJsonConsumer.post(userId, albums);
    }

    @Cacheable(value = "usersAlbums")
    public List<Album> getAlbumsByUserId(Long userId) {
        return albumJsonConsumer.getAlbumsByUserId(userId);
    }

    @CachePut(value = "usersAlbums", key = "userId")
    public List<Album> put(Long userId, List<Album> albums) {
        return albumJsonConsumer.put(userId, albums);
    }

    @CacheEvict("usersAlbums")
    public void deleteAlbumsByUserId(Long userId) {
        albumJsonConsumer.deleteAlbumsByUserId(userId);
    }

}
