package com.greg.todoc.task_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.greg.todoc.R;
import com.greg.todoc.di.DI;
import com.greg.todoc.model.Task;
import com.greg.todoc.service.TaskApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListActivity extends AppCompatActivity {

    private List<Task> mTask;
    private TaskApiService mApiService;
    @BindView(R.id.task_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApiService = DI.getTaskApiService();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initList();
    }

    public void initList(){
        mTask = mApiService.getTasks();
        mRecyclerView.setAdapter(new TaskRecyclerViewAdapter(mTask));
    }


}
