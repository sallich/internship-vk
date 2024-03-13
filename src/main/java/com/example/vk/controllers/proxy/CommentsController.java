package com.example.vk.controllers.proxy;

import com.example.vk.dto.Comment;
import com.example.vk.services.proxy.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsController{

    private final CommentsService commentsService;

    @PostMapping("/api/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> postCommentsByPostId(@PathVariable Long postId, @RequestBody List<Comment> comments) {
        return ResponseEntity.ok().body(commentsService.saveComments(postId, comments));
    }

    @GetMapping("/api/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok().body(commentsService.getCommentsByPostId(postId));
    }

    @DeleteMapping("/api/posts/{postId}/comments")
    public ResponseEntity<HttpStatus> deleteCommentsByPostId(@PathVariable Long postId) {
        commentsService.deleteCommentsByPostId(postId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/api/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> putComment(@PathVariable Long postId, @RequestBody List<Comment> comments) {
        return ResponseEntity.ok().body(commentsService.put(postId, comments));
    }

}
