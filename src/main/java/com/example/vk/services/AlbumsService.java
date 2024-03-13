package com.example.vk.services;

import com.example.vk.json_consumer.AlbumsJsonConsumer;
import com.example.vk.dto.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlbumsService {

    private final AlbumsJsonConsumer albumsJsonConsumer;

    @Cacheable("albums")
    public Album savePhotos(Long albumId, Album album){
        return albumsJsonConsumer.postByAlbumId(albumId, album);
    }

    @Cacheable("albums")
    public Set<Album> getPostByAlbumId(Long albumId){
        return albumsJsonConsumer.getByAlbumId(albumId);
    }

    @CacheEvict("albums")
    public void deletePostByAlbumId(Long albumId){
        albumsJsonConsumer.deleteByAlbumId(albumId);
    }

    @CachePut(value = "albums", key = "id")
    public Album put(Long id, Album album){
        return albumsJsonConsumer.put(id,album);
    }
}
