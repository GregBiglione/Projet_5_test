package com.greg.todoc.ui.task_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.greg.todoc.R;
import com.greg.todoc.model.Task;
import com.greg.todoc.viewmodel.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>{

    // For Data
    private List<Task> tasks = new ArrayList<>();
    //Context mContext;

    //public TaskRecyclerViewAdapter(List<Task> mTask, Context context) {
    //    this.mTask = mTask;
    //    this.mContext = context;
    //}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        //holder.mColor.setText(String.valueOf(currentTask.getProjectId()));
        holder.mTitle.setText(currentTask.getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.mCreationDate.setText(simpleDateFormat.format(currentTask.getDateOfCreation()));
        // @Query("SELECT * FROM Task WHERE id = :id")
        //    LiveData<List<Task>> getItems(long id);
        //Task task = mTask.get(position);
        //Drawable color = new ColorDrawable(ContextCompat.getColor(mContext, task.getColor()));
        //holder.mColor.setImageDrawable(color);
        //holder.mColor.setColorFilter(R.color.colorTartampion);
        //holder.mTitle.setText(task.getTitle());
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //holder.mCreationDate.setText(simpleDateFormat.format(task.getDateOfCreation()));

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //@Query("DELETE FROM Task WHERE id = :id")
                //int deleteTask(long id);
                //EventBus.getDefault().post(new DeleteTaskEvent(task));
            }
        });
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged(); //pas la meilleure solution à modifier plus tard
    }

    @Override
    public int getItemCount() {
        return tasks.size();
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
