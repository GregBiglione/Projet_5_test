package com.greg.todoc.service;

import com.greg.todoc.model.Task;

import java.util.List;

public class DummyTaskApiService implements TaskApiService{

    private List<Task> tasks = DummyTaskGenerator.generateTasks();

    @Override
    public List<Task> getTasks() {
        return tasks;
    }
}
