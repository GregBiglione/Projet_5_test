package com.greg.todoc.task_list;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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
    Context mContext;

    public TaskRecyclerViewAdapter(List<Task> mTask, Context context) {
        this.mTask = mTask;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // @Query("SELECT * FROM Task WHERE id = :id")
        //    LiveData<List<Task>> getItems(long id);
        Task task = mTask.get(position);
        Drawable color = new ColorDrawable(ContextCompat.getColor(mContext, task.getColor()));
        holder.mColor.setImageDrawable(color);
        holder.mTitle.setText(task.getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.mCreationDate.setText(simpleDateFormat.format(task.getDateOfCreation()));

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //@Query("DELETE FROM Task WHERE id = :id")
                //int deleteTask(long id);
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
