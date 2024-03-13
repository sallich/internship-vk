package com.example.vk.controllers;

import com.example.vk.dto.Post;
import com.example.vk.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class PostsController {
    private final PostService postService;


    @GetMapping("/api/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        return ResponseEntity.ok().body(postService.getById(id));
    }

    @GetMapping("/api/posts")
    public ResponseEntity<Set<Post>> getAllPosts(){
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @PostMapping("/api/posts")
    public ResponseEntity<Post> post(@RequestBody Post post) {
        return ResponseEntity.ok().body(postService.post(post));
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<Post> putPost(@RequestBody Post post, @PathVariable Long id) {

        return ResponseEntity.ok().body(postService.put(id,post));
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
