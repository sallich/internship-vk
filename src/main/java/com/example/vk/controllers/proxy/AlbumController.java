package com.example.vk.controllers.proxy;

import com.example.vk.dto.Album;
import com.example.vk.services.proxy.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlbumController{

    private final AlbumService albumService;

    @PostMapping("/api/users/{userId}/albums")
    public ResponseEntity<List<Album>> postAlbumByUserId(@PathVariable Long userId, @RequestBody List<Album> albums) {
        return ResponseEntity.ok().body(albumService.saveAlbums(userId, albums));
    }

    @GetMapping("/api/users/{userId}/albums")
    public ResponseEntity<List<Album>> getAlbumByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(albumService.getAlbumsByUserId(userId));
    }

    @PutMapping("/api/users/{userId}/albums")
    public ResponseEntity<List<Album>> putAlbum(@PathVariable Long userId, @RequestBody List<Album> albums) {
        return ResponseEntity.ok().body(albumService.put(userId, albums));
    }

    @DeleteMapping("/api/users/{userId}/albums")
    public ResponseEntity<HttpStatus> deleteAlbumByUserId(@PathVariable Long userId) {
        albumService.deleteAlbumsByUserId(userId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
