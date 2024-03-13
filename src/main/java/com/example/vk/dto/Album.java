package com.example.vk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Album {
    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;
    private Long albumId;
}
