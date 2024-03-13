package com.example.vk.controllers;

import com.example.vk.dto.UsersAlbums;
import com.example.vk.services.UsersAlbumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UsersAlbumsController {
    private final UsersAlbumsService usersAlbumsService;

    @PostMapping("/api/users/{userId}/albums")
    public ResponseEntity<UsersAlbums> postAlbumByUserId(@PathVariable Long userId, @RequestBody UsersAlbums usersAlbums) {
        return ResponseEntity.ok().body(usersAlbumsService.saveAlbums(userId,usersAlbums));
    }

    @GetMapping("/api/users/{userId}/albums")
    public ResponseEntity<Set<UsersAlbums>> getAlbumByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(usersAlbumsService.getAlbumsByUserId(userId));
    }

    @PutMapping("/api/users/{id}/albums")
    public ResponseEntity<UsersAlbums> putAlbum(@PathVariable Long id, @RequestBody UsersAlbums usersAlbums){
        return ResponseEntity.ok().body(usersAlbumsService.put(id,usersAlbums));
    }

    @DeleteMapping("/api/users/{userId}/albums")
    public ResponseEntity<HttpStatus> deleteAlbumByUserId(@PathVariable Long userId) {
        usersAlbumsService.deleteAlbumsByUserId(userId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
