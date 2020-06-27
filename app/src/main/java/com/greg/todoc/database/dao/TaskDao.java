package com.greg.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.greg.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE id = :id")
    LiveData<List<Task>>getTasks(long id);

    @Insert
    //void ?
    long insertTask(Task task);

    @Query("DELETE FROM Task WHERE id = :id")
    // void ?
    int deleteTask(long id);
}
