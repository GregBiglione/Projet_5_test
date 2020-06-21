package com.greg.todoc;

import androidx.core.content.ContextCompat;

import com.greg.todoc.di.DI;
import com.greg.todoc.model.Task;
import com.greg.todoc.service.DummyTaskApiService;
import com.greg.todoc.service.DummyTaskGenerator;
import com.greg.todoc.service.TaskApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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
        int lucidiaColor = R.color.colorTartampion;
        long id = 21;
        int image = lucidiaColor;
        String title = "Passer l'aspirateur";
        Date dateOfCreation = Calendar.getInstance().getTime();
        String project = "Projet Lucidia";
        Task taskToAdd = new Task(id, image, title, dateOfCreation, project);
        service.createTask(taskToAdd);
    }

    @Test
    public void taskByDate(){
        List<Task> actualTaskByDate = service.getTasks();
        Task task = actualTaskByDate.get(0);
        List<Task> expectedTaskByDate = service.getTasksByDates(new Date(120, 05, 05), new Date(120, 05, 10));
        assertThat(expectedTaskByDate.size(), is(1));
        assertTrue(expectedTaskByDate.contains(task));
    }

    @Test
    public void taskByProject(){
        List<Task> actualTaskByProject = service.getTasks();
        Task task = actualTaskByProject.get(4);
        List<Task> expectedTasByProject = service.getTasksByProject("Projet Tartampion");
        assertThat(expectedTasByProject.size(), is(1));
        assertTrue(expectedTasByProject.contains(task));
    }
}