package com.greg.todoc.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.greg.todoc.model.Task;
import com.greg.todoc.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private LiveData<List<Task>> mTasks;

    public TaskViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mTasks = mRepository.getTasks();
    }

    public LiveData<List<Task>>getTasks(){
        return mTasks;
    }

    public void insert(Task task){
        mRepository.insertTask(task);
    }

    public void delete(Task task){
        mRepository.deleteTask(task);
    }
}
