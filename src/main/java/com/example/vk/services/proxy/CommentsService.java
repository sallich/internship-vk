package com.example.vk.services.proxy;

import com.example.vk.dto.Comment;
import com.example.vk.json_consumer.CommentsJsonConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService{

    private final CommentsJsonConsumer commentsJsonConsumer;

    @Cacheable("comments")
    public List<Comment> saveComments(Long postId, List<Comment> comments) {
        return commentsJsonConsumer.post(postId, comments);
    }

    @Cacheable("comments")
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentsJsonConsumer.getByPostId(postId);
    }

    @CacheEvict("comments")
    public void deleteCommentsByPostId(Long postId) {
        commentsJsonConsumer.deleteByPostId(postId);
    }

    @CachePut(value = "comments", key = "postId")
    public List<Comment> put(Long postId, List<Comment> comments) {
        return commentsJsonConsumer.put(postId, comments);
    }

}
