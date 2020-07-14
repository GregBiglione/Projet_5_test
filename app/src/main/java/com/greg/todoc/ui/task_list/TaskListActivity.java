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
import com.greg.todoc.dialog_box.DateDialog;
import com.greg.todoc.dialog_box.ProjectDialog;
import com.greg.todoc.events.FilterByDateEvent;
import com.greg.todoc.events.FilterByProjectEvent;
import com.greg.todoc.model.Task;
import com.greg.todoc.viewmodel.TaskViewModel;

import org.greenrobot.eventbus.Subscribe;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class TaskListActivity extends AppCompatActivity {

    public static final int ADD_TASK_REQUEST_CODE = 1;
    @BindView(R.id.task_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.add_btn) FloatingActionButton mAdd;
    private TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initList();
        clickOnFabAdd();
    }

    public void initList(){
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
    }

    public void clickOnFabAdd(){
        mAdd = findViewById(R.id.add_btn);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaskListActivity.this, AddTaskActivity.class);
                startActivityForResult(i, ADD_TASK_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddTaskActivity.EXTRA_TITLE);
            Date dateOfCreation = (Date)data.getSerializableExtra(AddTaskActivity.EXTRA_DATE);
            int color = data.getIntExtra(AddTaskActivity.EXTRA_PROJECT, 1);

            Task task = new Task(title, dateOfCreation, color);
            mTaskViewModel.insert(task);
            Toasty.success(this, "Tâche enregistrée", Toasty.LENGTH_SHORT).show();
        }
        else{
            Toasty.error(this, "Tâche non enregistrée", Toasty.LENGTH_SHORT).show();
        }
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
                initList();
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
