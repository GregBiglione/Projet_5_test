//package com.greg.todoc.service;
//
//import com.greg.todoc.model.Task;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class DummyTaskApiService implements TaskApiService{
//
//    //private List<Task> tasks = DummyTaskGenerator.generateTasks();
//
//    @Override
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    /**
//     * Delete a Task
//     * @param task
//     */
//
//    @Override
//    //public void deleteTask(Task task) {
//        tasks.remove(task);
//    }
//
//    /**
//     * CReate a Task
//     * @param task
//     */
//
//    @Override
//    public void createTask(Task task) {
//        tasks.add(task);
//    }
//
//    @Override
//    public List<Task> getTasksByDates(Date startDate, Date endDate) {
//        List<Task> filterByDates = new ArrayList<>();
//        for (Task t: tasks)
//        {
//            if (t.getDateOfCreation().after(startDate) && t.getDateOfCreation().before(endDate))
//            {
//                filterByDates.add(t);
//            }
//        }
//        return filterByDates;
//    }
//
//    @Override
//    public List<Task> getTasksByProject(String selectedProject) {
//        List<Task> filterByProject = new ArrayList<>();
//        for (Task t: tasks)
//        {
//            if(t.getProject().equals(selectedProject))
//            {
//                filterByProject.add(t);
//            }
//        }
//        return filterByProject;
//    }
//}
//