package com.example.crud.controller;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("用户不存在: " + id));
        return ResponseEntity.ok(user);
    }

    // Update user role (admin only)
    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestBody Map<String, String> body) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("用户不存在: " + id));

        String roleStr = body.get("role");
        if (roleStr == null) {
            throw new ResponseStatusException(BAD_REQUEST, "角色不能为空");
        }

        try {
            User.Role role = User.Role.valueOf(roleStr);
            user.setRole(role);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, "无效的角色");
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("用户不存在: " + id));

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}
