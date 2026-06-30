package com.example.taskmanagement.controllers;

import com.example.taskmanagement.models.User;
import com.example.taskmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(name = "search", required = false) String search) {

        List<User> users = userService.findAllUsers();

        if (search != null && !search.trim().isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Trả về ResponseEntity với HTTP Status 200 (OK) và danh sách users ở body
        return ResponseEntity.ok(users);
    }
}