package com.greg.todoc.database;


import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.greg.todoc.R;
import com.greg.todoc.database.dao.TaskDao;
import com.greg.todoc.model.Project;
import com.greg.todoc.model.Task;


@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    //--------- SINGLETON --------------
    public static volatile TodocDatabase INSTANCE;

    //--------- DAO --------------
    public abstract TaskDao taskDao();

    //--------- INSTANCE --------------
    public static TodocDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (TodocDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "TodocDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    //--------- CALLBACK --------------
    private static Callback prepopulateDatabase(){
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();

                //--------- TASKS --------------
                contentValues.put("id", 1);
                contentValues.put("title", "Nettoyer les vitres");
                contentValues.put("dateOfCreation", 2020-7-7);
                contentValues.put("project_id", 1);

                contentValues.put("id", 2);
                contentValues.put("title", "Vider le lave vaisselle");
                contentValues.put("dateOfCreation", 2020-7-11);
                contentValues.put("project_id", 1);

                contentValues.put("id", 3);
                contentValues.put("title", "Passer l'aspirateur");
                contentValues.put("dateOfCreation", 2020-7-14);
                contentValues.put("project_id", 1);

                contentValues.put("id", 4);
                contentValues.put("title", "Arroser les plantes");
                contentValues.put("dateOfCreation", 2020-7-17);
                contentValues.put("project_id", 1);

                contentValues.put("id", 5);
                contentValues.put("title", "Nettoyer les toilettes");
                contentValues.put("dateOfCreation", 2020-7-23);
                contentValues.put("project_id", 1);

                //--------- PROJECTS --------------
                contentValues.put("id", 1);
                contentValues.put("name", "Projet Lucidia");
                contentValues.put("color", R.color.colorLucidia);

                contentValues.put("id", 2);
                contentValues.put("name", "Projet Circus");
                contentValues.put("color", R.color.colorCircus);

                contentValues.put("id", 3);
                contentValues.put("name", "Projet Tartampion");
                contentValues.put("color", R.color.colorTartampion);
            }
        };
    }
}
