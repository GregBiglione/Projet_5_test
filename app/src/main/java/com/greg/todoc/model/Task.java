package com.greg.todoc.model;

import android.graphics.Color;

import androidx.annotation.ColorInt;

import java.util.Date;

public class Task {
    long id;
    String title;
    Date dateOfCreation;
    int project_id;

    public Task(long id, String title, Date dateOfCreation, int project_id) {
        this.id = id;
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.project_id = project_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }
}
