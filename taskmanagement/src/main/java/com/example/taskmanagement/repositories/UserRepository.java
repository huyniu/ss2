package com.example.taskmanagement.repositories;

import com.example.taskmanagement.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1L, "admin_user", "admin@example.com", "ADMIN"));
        users.add(new User(2L, "dev_01", "dev1@example.com", "USER"));
        users.add(new User(3L, "tester_01", "tester@example.com", "USER"));
    }

    public List<User> findAll() {
        return users;
    }
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null); // Trả về null nếu không tìm thấy
    }
}