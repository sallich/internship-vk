package com.example.vk.repositories;

import com.example.vk.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User getUserById(Long id);
}