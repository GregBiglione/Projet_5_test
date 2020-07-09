package com.greg.todoc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.greg.todoc.database.TodocDatabase;
import com.greg.todoc.database.dao.ProjectDao;
import com.greg.todoc.database.dao.TaskDao;
import com.greg.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private ProjectDao mProjectDao;
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mTasks;

    public TaskRepository(Application application){
        TodocDatabase database = TodocDatabase.getInstance(application);
        mProjectDao = database.projectDao();
        mTaskDao = database.taskDao();
        mTasks = mTaskDao.getTasks();
    }

    public LiveData<List<Task>> getTasks(){
        return mTasks;
    }

    public void insertTask(Task task){
        new InsertTaskAsyncTask(mTaskDao).execute(task);
    }

    public void deleteTask(Task task){
        new DeleteTaskAsyncTask(mTaskDao).execute(task);
    }

    //------------ Async classes -------------------------------------------------------------------
    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao mTaskDao;

        public InsertTaskAsyncTask(TaskDao mTaskDao) {
            this.mTaskDao = mTaskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.insertTask(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao mTaskDao;

        public DeleteTaskAsyncTask(TaskDao mTaskDao) {
            this.mTaskDao = mTaskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.deleteTask(tasks[0]);
            return null;
        }
    }
}
