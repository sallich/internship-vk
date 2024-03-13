package com.example.vk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersToDo {
    private Long id;
    private String title;
    private Long userId;
    private Boolean completed;
}
