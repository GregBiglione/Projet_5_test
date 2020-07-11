package com.greg.todoc.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.greg.todoc.R;
import com.greg.todoc.database.dao.ProjectDao;
import com.greg.todoc.database.dao.TaskDao;
import com.greg.todoc.model.Project;
import com.greg.todoc.model.Task;

import java.util.Calendar;
import java.util.Date;


@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    //--------- SINGLETON --------------
    public static TodocDatabase instance;

    //--------- DAO --------------
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    //--------- INSTANCE --------------
    public static synchronized TodocDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodocDatabase.class, "TodocDatabase.db")
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    //--------- CALLBACK --------------
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PrepopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PrepopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private TaskDao mTaskDao;
        private ProjectDao mProjectDao;

        private PrepopulateDbAsyncTask(TodocDatabase db){
            mTaskDao = db.taskDao();
            mProjectDao = db.projectDao();
        }

        public static Date setTaskDate(int year, int month, int day){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            return c.getTime();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //--------- PROJECTS --------------
            mProjectDao.createProject(new Project("Projet Lucidia", R.color.colorLucidia));
            mProjectDao.createProject(new Project("Projet Circus", R.color.colorCircus));
            mProjectDao.createProject(new Project("Projet Tartampion", R.color.colorTartampion));

            //--------- TASKS --------------
            mTaskDao.insertTask(new Task("Nettoyer les vitres", setTaskDate(2020, 8, 7), 1));
            mTaskDao.insertTask(new Task("Vider le lave vaisselle", setTaskDate(2020, 8, 11), 2));
            mTaskDao.insertTask(new Task("Passer l'aspirateur" , setTaskDate(2020, 8, 14), 2));
            mTaskDao.insertTask(new Task("Arroser les plantes", setTaskDate(2020, 8, 17), 1));
            mTaskDao.insertTask(new Task("Nettoyer les toilettes", setTaskDate(2020, 8, 23), 3));
            return null;
        }
    }
}