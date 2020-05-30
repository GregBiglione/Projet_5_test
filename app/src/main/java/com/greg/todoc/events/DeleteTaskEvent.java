package com.greg.todoc.events;

import com.greg.todoc.model.Task;

public class DeleteTaskEvent {

    public Task task;

    /**
     * Constructor
     * @param task
     */

    public DeleteTaskEvent(Task task) {
        this.task = task;
    }
}
