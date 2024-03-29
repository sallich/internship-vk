package com.example.vk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment{
    private Long id;
    private String name;
    private String body;
    private Long postId;
    private String email;
}
