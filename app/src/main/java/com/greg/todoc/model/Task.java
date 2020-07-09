package com.greg.todoc.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.greg.todoc.DateConverter;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Project.class,
        parentColumns = "id",
        childColumns = "projectId"),
        indices = {@Index(value = {"id"}, unique = true),@Index(value = {"projectId"})})

public class Task {

    @PrimaryKey(autoGenerate = true)
    long id;
    String title;
    @TypeConverters(DateConverter.class)
    Date dateOfCreation;
    @ColumnInfo(name = "projectId")
    long projectId;

    public Task(String title, Date dateOfCreation, long projectId) {
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.projectId = projectId;
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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }
}
