package com.example.vk.controllers;

import com.example.vk.dto.UsersToDo;
import com.example.vk.services.UsersToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UsersToDoController {

    private final UsersToDoService usersToDoService;

    @PostMapping("/api/users/{userId}/todos")
    public ResponseEntity<UsersToDo> postToDoByUserId(@PathVariable Long userId, @RequestBody UsersToDo usersToDo) {
        return ResponseEntity.ok().body(usersToDoService.post(userId,usersToDo));
    }

    @GetMapping("/api/users/{userId}/todos")
    public ResponseEntity<Set<UsersToDo>> getToDoByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(usersToDoService.getToDoByUserId(userId));
    }

    @PutMapping("/api/users/{id}/todos")
    public ResponseEntity<UsersToDo> putToDo(@PathVariable Long id, @RequestBody UsersToDo usersToDo){
        return ResponseEntity.ok().body(usersToDoService.put(id,usersToDo));
    }

    @DeleteMapping("/api/users/{userId}/todos")
    public ResponseEntity<HttpStatus> deleteToDoByUserId(@PathVariable Long userId) {
        usersToDoService.deleteTodoByUserId(userId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
