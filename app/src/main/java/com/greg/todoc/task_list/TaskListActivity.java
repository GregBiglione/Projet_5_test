package com.greg.todoc.task_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greg.todoc.R;
import com.greg.todoc.di.DI;
import com.greg.todoc.dialog_box.AddDialog;
import com.greg.todoc.dialog_box.ProjectDialog;
import com.greg.todoc.events.DeleteTaskEvent;
import com.greg.todoc.model.Task;
import com.greg.todoc.service.TaskApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListActivity extends AppCompatActivity {

    private List<Task> mTask;
    private TaskApiService mApiService;
    @BindView(R.id.task_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.add_btn) FloatingActionButton mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApiService = DI.getTaskApiService();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initList();

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();
            }
        });
    }

    public void initList(){
        mTask = mApiService.getTasks();
        mRecyclerView.setAdapter(new TaskRecyclerViewAdapter(mTask));
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
        mApiService.deleteTask(event.task);
        initList();
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.by_date:
                break;
            case R.id.by_project:
                ProjectDialog projectDialog = new ProjectDialog();
                projectDialog.showNow(getSupportFragmentManager(), "Project dialog");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
