package com.example.vk.services;

import com.example.vk.dto.UsersAlbums;
import com.example.vk.json_consumer.UsersAlbumsJsonConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsersAlbumsService {

    private final UsersAlbumsJsonConsumer usersAlbumsJsonConsumer;

    public UsersAlbums saveAlbums(Long userId, UsersAlbums usersAlbums){
        return usersAlbumsJsonConsumer.post(userId,usersAlbums);
    }

    @Cacheable(value = "usersAlbums")
    public Set<UsersAlbums> getAlbumsByUserId(Long userId){
        return usersAlbumsJsonConsumer.getAlbumsByUserId(userId);
    }

    @CachePut(value = "usersAlbums", key = "usersAlbums.userId")
    public UsersAlbums put(Long id, UsersAlbums usersAlbums){
        return usersAlbumsJsonConsumer.put(id,usersAlbums);
    }

    @CacheEvict("usersAlbums")
    public void deleteAlbumsByUserId(Long userId){
        usersAlbumsJsonConsumer.deleteAlbumsByUserId(userId);
    }

}
