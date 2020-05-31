package com.greg.todoc.service;

import com.greg.todoc.R;
import com.greg.todoc.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyTaskGenerator {

    public static List<Task> DUMMY_TASK = Arrays.asList(
            new Task(1, R.color.colorLucidia, "Nettoyer les vitres", new Date(24/05/2020) , "Projet Lucidia"),
            new Task(2, R.color.colorCircus, "Vider le lave vaisselle", new Date(28/05/2020), "Projet Circus"),
            new Task(3, R.color.colorCircus, "Passer l'aspirateur", new Date(3/06/2020), "Projet Circus"),
            new Task(4, R.color.colorLucidia, "Arroser les plantes", new Date(5/06/2020), "Projet Lucidia"),
            new Task(5, R.color.colorTartampion, "Nettoyer les toilettes", new Date(6/06/2020), "Projet Tartampion")
    );

    static List<Task> generateTasks()
    {
        return new ArrayList<>(DUMMY_TASK);
    }
}
