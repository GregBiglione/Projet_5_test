package com.greg.todoc.service;

import android.graphics.Color;

import com.greg.todoc.R;
import com.greg.todoc.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyTaskGenerator {

    public static List<Task> DUMMY_TASK = Arrays.asList(
            new Task(1, R.drawable.lucidia, "Nettoyer les vitres", new Date(120, 05, 06) , "Projet Lucidia"),
            new Task(2, R.drawable.circus, "Vider le lave vaisselle", new Date(120, 05, 11), "Projet Circus"),
            new Task(3, R.drawable.circus, "Passer l'aspirateur", new Date(120, 05, 14), "Projet Circus"),
            new Task(4, R.drawable.lucidia, "Arroser les plantes", new Date(120, 05, 17), "Projet Lucidia"),
            new Task(5, R.drawable.tartampion, "Nettoyer les toilettes", new Date(120, 05, 23), "Projet Tartampion")
    );

    static List<Task> generateTasks()
    {
        return new ArrayList<>(DUMMY_TASK);
    }
}
