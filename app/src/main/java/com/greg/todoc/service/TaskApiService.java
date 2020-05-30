package com.greg.todoc.service;

import com.greg.todoc.model.Task;

import java.util.List;

public interface TaskApiService {

    /**
     * Get Task list
     */
    List<Task> getTasks();

    /**
     * Delete a Task
     */
    void deleteTask(Task task);
}
