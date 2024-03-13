package com.example.vk.controllers.proxy;

import com.example.vk.dto.Post;
import com.example.vk.services.proxy.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Похож на PostController, но предназначен для работы с всеми альбомами пользователя
 */
@RestController
@RequiredArgsConstructor
public class PostsController{
    private final PostsService postsService;

    @PostMapping("/api/users/{userId}/posts")
    public ResponseEntity<List<Post>> postPostByUserId(@PathVariable Long userId, @RequestBody List<Post> posts) {
        return ResponseEntity.ok().body(postsService.savePosts(userId, posts));
    }

    @GetMapping("/api/users/{userId}/posts")
    public ResponseEntity<List<Post>> getPostByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(postsService.getPostsByUserId(userId));
    }

    @PutMapping("/api/users/{userId}/posts")
    public ResponseEntity<List<Post>> putPost(@PathVariable Long userId, @RequestBody List<Post> posts) {
        return ResponseEntity.ok().body(postsService.put(userId, posts));
    }

    @DeleteMapping("/api/users/{userId}/posts")
    public ResponseEntity<HttpStatus> deletePostByUserId(@PathVariable Long userId) {
        postsService.deletePostsByUserId(userId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
