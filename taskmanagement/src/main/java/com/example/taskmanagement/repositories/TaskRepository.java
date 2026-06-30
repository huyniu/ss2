package com.example.taskmanagement.repositories;

import com.example.taskmanagement.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        for (long i = 1; i <= 10; i++) {
            tasks.add(new Task(
                    i,
                    "Task " + i,
                    "Mô tả cho task " + i,
                    (i % 2 == 0) ? "High" : "Normal",
                    "dev_0" + ((i % 3) + 1)
            ));
        }
    }

    public List<Task> findAll() {
        return tasks;
    }
}