package com.example.vk.controllers.proxy;

import com.example.vk.dto.Todo;
import com.example.vk.services.proxy.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController{

    private final TodoService todoService;

    @PostMapping("/api/users/{userId}/todos")
    public ResponseEntity<List<Todo>> postToDoByUserId(@PathVariable Long userId, @RequestBody List<Todo> todos) {
        return ResponseEntity.ok().body(todoService.post(userId, todos));
    }

    @GetMapping("/api/users/{userId}/todos")
    public ResponseEntity<List<Todo>> getToDoByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(todoService.getToDoByUserId(userId));
    }

    @PutMapping("/api/users/{userId}/todos")
    public ResponseEntity<List<Todo>> putToDo(@PathVariable Long userId, @RequestBody List<Todo> todos){
        return ResponseEntity.ok().body(todoService.put(userId, todos));
    }

    @DeleteMapping("/api/users/{userId}/todos")
    public ResponseEntity<HttpStatus> deleteToDoByUserId(@PathVariable Long userId) {
        todoService.deleteTodoByUserId(userId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
