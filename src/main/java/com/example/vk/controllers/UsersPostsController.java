package com.example.vk.controllers;

import com.example.vk.dto.UsersPosts;
import com.example.vk.services.UsersPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UsersPostsController {
    private final UsersPostsService usersPostsService;

    @PostMapping("/api/users/{userId}/posts")
    public ResponseEntity<UsersPosts> postPostByUserId(@PathVariable Long userId, @RequestBody UsersPosts usersPosts) {
        return ResponseEntity.ok().body(usersPostsService.savePosts(userId,usersPosts));
    }

    @GetMapping("/api/users/{userId}/posts")
    public ResponseEntity<Set<UsersPosts>> getPostByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(usersPostsService.getPostsByUserId(userId));
    }

    @PutMapping("/api/users/{id}/posts")
    public ResponseEntity<UsersPosts> putPost(@PathVariable Long id, @RequestBody UsersPosts usersPosts){
        return ResponseEntity.ok().body(usersPostsService.put(id,usersPosts));
    }

    @DeleteMapping("/api/users/{userId}/posts")
    public ResponseEntity<HttpStatus> deletePostByUserId(@PathVariable Long userId) {
        usersPostsService.deletePostsByUserId(userId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
