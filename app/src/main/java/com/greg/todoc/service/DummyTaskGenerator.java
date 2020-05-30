package com.greg.todoc.service;

import com.greg.todoc.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyTaskGenerator {

    public static List<Task> DUMMY_TASK = Arrays.asList(
            new Task(1, "https://www.1000patch.com/Files/20333/Img/11/p52--640x480-.jpg", "Nettoyer les vitres", new Date(24/05/2020) , "Projet Lucidia"),
            new Task(2, "https://www.encens-naturel.com/images/small_124.jpg", "Vider le lave vaisselle", new Date(28/05/2020), "Projet Circus"),
            new Task(3, "https://www.encens-naturel.com/images/small_124.jpg", "Passer l'aspirateur", new Date(3/06/2020), "Projet Circus"),
            new Task(4, "https://www.1000patch.com/Files/20333/Img/11/p52--640x480-.jpg", "Arroser les plantes", new Date(5/06/2020), "Projet Lucidia"),
            new Task(5, "https://www.ecolaines.com/1717-22182-thickbox/tissu-uni-patchwork-bleu-ciel.jpg", "Nettoyer les toilettes", new Date(6/06/2020), "Projet Tartampion")
    );

    static List<Task> generateTasks()
    {
        return new ArrayList<>(DUMMY_TASK);
    }
}
