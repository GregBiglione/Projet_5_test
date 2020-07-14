package com.greg.todoc.ui.task_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.greg.todoc.R;
import com.greg.todoc.model.Project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class AddTaskActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.greg.todoc.ui.task_list.EXTRA_TITLE";
    public static final String EXTRA_DATE = "com.greg.todoc.ui.task_list.EXTRA_DATE";
    public static final String EXTRA_PROJECT = "com.greg.todoc.ui.task_list.EXTRA_PROJECT";

    @BindView(R.id.imageColor) CircleImageView mColor;
    @BindView(R.id.addTaskInput) TextInputLayout mAddTaskInput;
    @BindView(R.id.addTaskEt) TextInputEditText mAddTitle;
    @BindView(R.id.dateOfCreationInput) TextInputLayout mDateInput;
    @BindView(R.id.DateEt) TextInputEditText mAddDate;
    @BindView(R.id.projectSpinner) Spinner mSpinner;

    @BindView(R.id.addTask) Button mAddBtn;
    @BindView(R.id.cancelAdd) Button mCancelAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        projectSelected();
        clickCancelBtn();
        clickAddBtn();
    }

    public String titleSelected(){
        mAddTitle = findViewById(R.id.addTaskEt);
        mAddTaskInput = findViewById(R.id.addTaskInput);
        String title = mAddTaskInput.getEditText().getText().toString().trim();
        return title;
    }

    public Date creationDate(){
        Date dateOfCreation = Calendar.getInstance().getTime();
        return dateOfCreation;
    }

    public void projectSelected(){
        mSpinner = findViewById(R.id.projectSpinner);
        List<Project> projects = new ArrayList<>();
        Project project1 = new Project("Projet Lucidia", R.color.colorLucidia);
        projects.add(project1);
        Project project2 = new Project("Projet Circus", R.color.colorCircus);
        projects.add(project2);
        Project project3 = new Project("Projet Tartampion", R.color.colorTartampion);
        projects.add(project3);

        ArrayAdapter<Project> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, projects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position).equals(projects.get(position).getColor());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //int color = mColor.setBackgroundColor();
    }

    public void getSelectedProject(View v){
        Project project = (Project) mSpinner.getSelectedItem();
    }

    public void projectColor(Project project){
        int color = project.getColor();
    }

    public void clickAddBtn(){
        mAddBtn = findViewById(R.id.addTask);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    public void saveTask(){
        //int color = mSpinner.setBackgroundColor(projects.getColor());
        int color = R.color.colorTartampion;

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, titleSelected());
        data.putExtra(EXTRA_DATE, creationDate());
        data.putExtra(EXTRA_PROJECT, color);

        setResult(RESULT_OK, data);
        finish();
    }

    public void clickCancelBtn(){
        mCancelAdd = findViewById(R.id.cancelAdd);
        mCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainActivity = new Intent(AddTaskActivity.this, TaskListActivity.class);
                startActivity(backToMainActivity);
                Toasty.warning(getApplicationContext(), "Tâche non enregistrée", Toasty.LENGTH_SHORT).show();
            }
        });
    }
}
