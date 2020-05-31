package com.greg.todoc.service;

import com.greg.todoc.model.Task;

import java.util.List;

public class DummyTaskApiService implements TaskApiService{

    private List<Task> tasks = DummyTaskGenerator.generateTasks();

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Delete a Task
     * @param task
     */

    @Override
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * CReate a Task
     * @param task
     */

    @Override
    public void createTask(Task task) {
        tasks.add(task);
    }
}
