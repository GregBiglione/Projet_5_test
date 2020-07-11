package com.greg.todoc.ui.task_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greg.todoc.R;
import com.greg.todoc.dialog_box.AddDialog;
import com.greg.todoc.dialog_box.DateDialog;
import com.greg.todoc.dialog_box.ProjectDialog;
import com.greg.todoc.events.DeleteTaskEvent;
import com.greg.todoc.events.FilterByDateEvent;
import com.greg.todoc.events.FilterByProjectEvent;
import com.greg.todoc.model.Task;
import com.greg.todoc.viewmodel.TaskViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class TaskListActivity extends AppCompatActivity {

    @BindView(R.id.task_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.add_btn) FloatingActionButton mAdd;
    private TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.task_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter();
        mRecyclerView.setAdapter(adapter); //sera vide par défaut

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
               adapter.setTasks(tasks); // a chaque changement sur les tâches l'adapter sera mis à jour
            }
        });
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteTask(DeleteTaskEvent event){
    }

    public void openAddDialog(){
        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "Add dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.by_all:
                //initList();
                break;
            case R.id.by_date:
                DateDialog dateDialog = new DateDialog();
                dateDialog.show(getSupportFragmentManager(), "Date dialog");
                break;
            case R.id.by_project:
                ProjectDialog projectDialog = new ProjectDialog();
                projectDialog.show(getSupportFragmentManager(), "Project dialog");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onFilterBydate(FilterByDateEvent event){
        //mTask = mApiService.getTasksByDates(event.getStartDate(), event.getEnddate());
        //mRecyclerView.setAdapter(new TaskRecyclerViewAdapter(mTask, this));
    }

    @Subscribe
    public void onFilterByProject(FilterByProjectEvent event){
        //mTask = mApiService.getTasksByProject(event.getProjectSelected());
        //mRecyclerView.setAdapter(new TaskRecyclerViewAdapter(mTask, this));
    }
}
