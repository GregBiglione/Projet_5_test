package com.greg.todoc.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.greg.todoc.R;
import com.greg.todoc.di.DI;
import com.greg.todoc.model.Task;
import com.greg.todoc.service.TaskApiService;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class AddDialog extends AppCompatDialogFragment {

    private TaskApiService mApiService;
    @BindView(R.id.imageColor) CircleImageView mColor;
    @BindView(R.id.addTaskInput) TextInputLayout mAddTaskInput;
    @BindView(R.id.addTaskEt) EditText mAddTitle;
    @BindView(R.id.dateOfCreationInput) TextInputLayout mDateInput;
    @BindView(R.id.DateEt) EditText mAddDate;
    @BindView(R.id.projectSpinner) Spinner mSpinner;

    @BindView(R.id.addDialog) Button mAddBtn;
    @BindView(R.id.cancelAddDialog) Button mCancelAdd;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mApiService = DI.getTaskApiService();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_add_dialog, null);
        mSpinner = view.findViewById(R.id.projectSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.project_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mAddTaskInput = view.findViewById(R.id.addTaskInput);
        mAddTitle = view.findViewById(R.id.addTaskEt);

        // Button
        mAddBtn = view.findViewById(R.id.addDialog);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date dateOfCreation = Calendar.getInstance().getTime();

                Task task = new Task(
                        System.currentTimeMillis(),
                        R.drawable.tartampion,
                        mAddTaskInput.getEditText().getText().toString().trim(),
                        dateOfCreation,
                        mSpinner.getSelectedItem().toString().trim()
                );
                mApiService.createTask(task);
                Toasty.success(getActivity(), "Tâche enregistrée", Toasty.LENGTH_SHORT).show();
                dismiss();
            }
        });

        mCancelAdd = view.findViewById(R.id.cancelAddDialog);
        mCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    public void init(){
        //mColor = taskSelection();
        Glide.with(getActivity())
                .load(R.drawable.lucidia)
                .into(mColor);
        mAddTaskInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {mAddBtn.setEnabled(s.length() > 0);}
        });
    }

    void taskColorFromItemSelection(){
        mSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (getId())
                {
                    case 0:
                        mColor.setBackgroundColor(R.drawable.circus);
                        break;
                    case 1:
                        mColor.setBackgroundColor(R.drawable.lucidia);
                        break;
                    case 2:
                        mColor.setBackgroundColor(R.drawable.tartampion);
                        break;
                }
            }
        });
    }
}

//id value
//System.currentTimeMillis();
//
//image (color depending of spinner item selected)
//int lucidiaColor = ContextCompat.getColor(getActivity(), R.drawable.circus);
//int circusColor = ContextCompat.getColor(getActivity(), R.color.colorCircus);
//int tartampionColor = ContextCompat.getColor(getActivity(), R.color.colorTartampion);
//mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        switch(getId())
//        {
//            case 0:
//                mColor.setBackgroundColor(R.drawable.circus);
//                break;
//            case 1:
//                mColor.setBackgroundColor(R.drawable.lucidia);
//                break;
//            case 2:
//                mColor.setBackgroundColor(R.drawable.tartampion);
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//});

//task title (edit text)
//mAddTaskInput.getEditText().getText().toString().trim();

//date of creation
//Calendar c = Calendar.getInstance();
//String currentDate = DateFormat.getDateInstance().format(c.getTime());
//mAddDate.setText(currentDate);
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//try {
//    Date today = simpleDateFormat.parse(mDateInput.getEditText().getText().toString().trim());
//} catch (ParseException e) {
//    e.printStackTrace();
//}

// task project (spinner value)
//mSpinner.getSelectedItem().toString().trim();

//Calendar c = Calendar.getInstance();
//String currentDate = DateFormat.getDateInstance().format(c.getTime());
//mAddDate.setText(currentDate);
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//try {
//    Date today = simpleDateFormat.parse(mDateInput.getEditText().getText().toString().trim());
//} catch (ParseException e) {
//    e.printStackTrace();
//}