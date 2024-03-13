package com.example.vk.controllers.registration;

import com.example.vk.entities.User;
import com.example.vk.services.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/alluser")
    public ResponseEntity<List<User>> getAllUserById() {
        return ResponseEntity.ok().body(userService.allUsers());
    }

    @PostMapping("/user/new")
    public ResponseEntity<HttpStatus> postUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
