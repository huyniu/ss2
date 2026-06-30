package com.example.taskmanagement.controllers;

import com.example.taskmanagement.models.Task;
import com.example.taskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    public ResponseEntity<?> createNewTask(@RequestBody Task newTask) {
        boolean isCreated = taskService.createTask(newTask);

        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Tạo task thành công!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi: Người dùng (assignedTo) không tồn tại!");
        }
    }
}