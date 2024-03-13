package com.example.vk.services;

import com.example.vk.dto.Comments;
import com.example.vk.json_consumer.CommentsJsonConsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsJsonConsumer commentsJsonConsumer;

    @Cacheable("comments")
    public Comments saveComments(Long postId, Comments comments){
        return commentsJsonConsumer.post(postId, comments);
    }

    @Cacheable("comments")
    public Set<Comments> getCommentsByPostId(Long postId){
        return commentsJsonConsumer.getByPostId(postId);
    }

    @CacheEvict("comments")
    public void deleteCommentsByPostId(Long postId){
        commentsJsonConsumer.deleteByPostId(postId);
    }

    @CachePut(value = "comments",key = "postId")
    public Comments put(Long postId, Comments comments){
        return commentsJsonConsumer.put(postId,comments);
    }

}
