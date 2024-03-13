package com.example.vk.controllers;

import com.example.vk.dto.Comments;
import com.example.vk.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/api/posts/{postId}/comments")
    public ResponseEntity<Comments> postCommentsByPostId(@PathVariable Long postId, @RequestBody Comments comments) {
        return ResponseEntity.ok().body(commentsService.saveComments(postId, comments));
    }

    @GetMapping("/api/posts/{postId}/comments")
    public ResponseEntity<Set<Comments>> getCommentsByPostId(@PathVariable Long postId){
        return ResponseEntity.ok().body(commentsService.getCommentsByPostId(postId));
    }

    @DeleteMapping("/api/posts/{postId}/comments")
    public ResponseEntity<HttpStatus> deleteCommentsByPostId(@PathVariable Long postId) {
        commentsService.deleteCommentsByPostId(postId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/api/posts/{postId}/comments")
    public ResponseEntity<Comments> putComment(@PathVariable Long postId, @RequestBody Comments comments){
        return ResponseEntity.ok().body(commentsService.put(postId,comments));
    }

}
