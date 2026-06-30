package com.example.taskmanagement.services;

import com.example.taskmanagement.models.Task;
import com.example.taskmanagement.models.User;
import com.example.taskmanagement.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
    public boolean createTask(Task newTask) {
        try {
            Long userId = Long.parseLong(newTask.getAssignedTo());
            User existingUser = userService.findUserById(userId);

            if (existingUser != null) {
                taskRepository.save(newTask);
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
}