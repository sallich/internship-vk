package com.example.vk.controllers;

import com.example.vk.dto.Album;
import com.example.vk.services.AlbumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AlbumsController {

    private final AlbumsService albumsService;

    @PostMapping("/api/posts/{albumId}/photos")
    public ResponseEntity<Album> postAlbumByAlbumId(@PathVariable Long albumId, @RequestBody Album album) {
        return ResponseEntity.ok().body(albumsService.savePhotos(albumId,album));
    }

    @GetMapping("/api/posts/{albumId}/photos")
    public ResponseEntity<Set<Album>> getAlbumByAlbumId(@PathVariable Long albumId){
        return ResponseEntity.ok().body(albumsService.getPostByAlbumId(albumId));
    }

    @PutMapping("/api/posts/{id}/photos")
    public ResponseEntity<Album> putPhoto(@PathVariable Long id, @RequestBody Album album){
        return ResponseEntity.ok().body(albumsService.put(id,album));
    }

    @DeleteMapping("/api/posts/{albumId}/photos")
    public ResponseEntity<HttpStatus> deleteAlbumByAlbumId(@PathVariable Long albumId) {
        albumsService.deletePostByAlbumId(albumId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
