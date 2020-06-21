package com.greg.todoc.model;

import android.graphics.Color;

import androidx.annotation.ColorInt;

import java.util.Date;

public class Task {
    long id;
    int color;
    String title;
    Date dateOfCreation;
    String project;

    public Task(long id, int color, String title, Date dateOfCreation, String project) {
        this.id = id;
        this.color = color;
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int image) {
        this.color = color;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }
}
