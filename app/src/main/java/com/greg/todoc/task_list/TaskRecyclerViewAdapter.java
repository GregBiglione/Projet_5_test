package com.greg.todoc.task_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.greg.todoc.R;
import com.greg.todoc.events.DeleteTaskEvent;
import com.greg.todoc.model.Task;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>{

    public List<Task> mTask;

    public TaskRecyclerViewAdapter(List<Task> mTask) {
        this.mTask = mTask;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mTask.get(position);
        Glide.with(holder.mColor.getContext())
                .load(task.getImage())
                .into(holder.mColor);
        holder.mTitle.setText(task.getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.mCreationDate.setText(simpleDateFormat.format(task.getDateOfCreation()));

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteTaskEvent(task));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_relative_lyt) RelativeLayout mRelativeLayout;
        @BindView(R.id.color_item) ImageView mColor;
        @BindView(R.id.title_tv) TextView mTitle;
        @BindView(R.id.creation_date_tv) TextView mCreationDate;
        @BindView(R.id.delete_button) ImageButton mDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
