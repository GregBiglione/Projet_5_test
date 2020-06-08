package com.greg.todoc;

import androidx.core.content.ContextCompat;

import com.greg.todoc.di.DI;
import com.greg.todoc.model.Task;
import com.greg.todoc.service.DummyTaskApiService;
import com.greg.todoc.service.DummyTaskGenerator;
import com.greg.todoc.service.TaskApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;


/**
 * Unit tests on Task Service
 */

@RunWith(JUnit4.class)
public class TaskServiceTest{

    TaskApiService service;

    @Before
    public void setUp(){
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getTasksWithSuccess(){
        List<Task> tasks = service.getTasks();
        List<Task> exectedTasks = DummyTaskGenerator.DUMMY_TASK;
        assertThat(tasks, IsIterableContainingInAnyOrder.containsInAnyOrder(exectedTasks.toArray()));
    }

    @Test
    public void deleteTaskWithSuccess(){
        Task taskToDelete = service.getTasks().get(0);
        service.deleteTask(taskToDelete);
        assertFalse(service.getTasks().contains(taskToDelete));
    }

    @Test
    public void addTaskWithSuccess(){
        int lucidiaColor = R.drawable.tartampion;
        long id = 21;
        int image = lucidiaColor;
        String title = "Passer l'aspirateur";
        Date dateOfCreation = new Date(121, 6, 4);
        String project = "Projet Lucidia";
        Task taskToAdd = new Task(id, image, title, dateOfCreation, project);
        service.createTask(taskToAdd);
    }
}