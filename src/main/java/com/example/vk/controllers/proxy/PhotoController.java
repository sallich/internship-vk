package com.example.vk.controllers.proxy;

import com.example.vk.dto.Photo;
import com.example.vk.services.proxy.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoController{

    private final PhotoService photoService;

    @PostMapping("/api/posts/{albumId}/photos")
    public ResponseEntity<List<Photo>> postAlbumByAlbumId(@PathVariable Long albumId, @RequestBody List<Photo> photos) {
        return ResponseEntity.ok().body(photoService.savePhotos(albumId, photos));
    }

    @GetMapping("/api/posts/{albumId}/photos")
    public ResponseEntity<List<Photo>> getAlbumByAlbumId(@PathVariable Long albumId) {
        return ResponseEntity.ok().body(photoService.getPostByAlbumId(albumId));
    }

    @PutMapping("/api/posts/{albumId}/photos")
    public ResponseEntity<List<Photo>> putPhoto(@PathVariable Long albumId, @RequestBody List<Photo> photos) {
        return ResponseEntity.ok().body(photoService.put(albumId, photos));
    }

    @DeleteMapping("/api/posts/{albumId}/photos")
    public ResponseEntity<HttpStatus> deleteAlbumByAlbumId(@PathVariable Long albumId) {
        photoService.deletePostByAlbumId(albumId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
