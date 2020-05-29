package com.greg.todoc.service;

import com.greg.todoc.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyTaskService {

    public static List<Task> DUMMY_TASK = Arrays.asList(
            new Task(1, "rose", "Nettoyer les vitres", new Date(24/05/2020) , "Projet Lucidia"),
            new Task(2, "vert", "Vider le lave vaisselle", new Date(28/05/2020), "Projet Circus"),
            new Task(3, "vert", "Passer l'aspirateur", new Date(3/06/2020), "Projet Circus"),
            new Task(4, "rose", "Arroser les plantes", new Date(5/06/2020), "Projet Lucidia"),
            new Task(5, "bleu", "Nettoyer les toilettes", new Date(6/06/2020), "Projet Tartampion")
    );

    static List<Task> generateTasks()
    {
        return new ArrayList<>(DUMMY_TASK);
    }
}
