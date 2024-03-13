package com.example.vk.services.proxy;

import com.example.vk.dto.Photo;
import com.example.vk.json_consumer.PhotoJsonConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PhotoService{

    private final PhotoJsonConsumer photoJsonConsumer;

    @Cacheable("photos")
    public List<Photo> savePhotos(Long albumId, List<Photo> photos) {
        return photoJsonConsumer.postByAlbumId(albumId, photos);
    }

    @Cacheable("photos")
    public List<Photo> getPostByAlbumId(Long albumId) {
        return photoJsonConsumer.getByAlbumId(albumId);
    }

    @CacheEvict("photos")
    public void deletePostByAlbumId(Long albumId) {
        photoJsonConsumer.deleteByAlbumId(albumId);
    }

    @CachePut(value = "photos", key = "albumId")
    public List<Photo> put(Long albumId, List<Photo> photos) {
        return photoJsonConsumer.put(albumId, photos);
    }
}
