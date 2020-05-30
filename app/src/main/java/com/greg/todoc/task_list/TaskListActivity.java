package com.greg.todoc.task_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greg.todoc.R;
import com.greg.todoc.di.DI;
import com.greg.todoc.dialog_box.AddDialog;
import com.greg.todoc.events.DeleteTaskEvent;
import com.greg.todoc.model.Task;
import com.greg.todoc.service.TaskApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
}
