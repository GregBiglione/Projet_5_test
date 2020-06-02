package com.greg.todoc.service;

import com.greg.todoc.model.Task;

import java.util.Date;
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

    /**
     * Create a task
     * @param task
     */
    void createTask(Task task);

    /**
     * Get Task list filtered by dates
     */
    List<Task> getTasksByDates(Date startDate, Date endDate);

    /**
     * Get Task list filtered by project
     */
    List<Task> getTasksByProject(String selectedProject);
}
