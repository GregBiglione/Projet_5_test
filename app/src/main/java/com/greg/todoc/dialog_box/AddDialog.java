package com.greg.todoc.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
//import com.greg.todoc.di.DI;
import com.greg.todoc.model.Task;

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

    @BindView(R.id.imageColor) CircleImageView mColor;
    @BindView(R.id.addTaskInput) TextInputLayout mAddTaskInput;
    @BindView(R.id.addTaskEt) EditText mAddTitle;
    @BindView(R.id.dateOfCreationInput) TextInputLayout mDateInput;
    @BindView(R.id.DateEt) EditText mAddDate;
    @BindView(R.id.projectSpinner) Spinner mSpinner;

    @BindView(R.id.addDialog) Button mAddBtn;
    @BindView(R.id.cancelAddDialog) Button mCancelAdd;

    public static final String EXTRA_TITLE = "com.greg.todoc.dialog_box.EXTRA_TITLE";
    public static final String DATE_OF_CREATION = "com.greg.todoc.dialog_box.DATE_OF_CREATION";
    public static final String EXTRA_PROJECT = "com.greg.todoc.dialog_box.EXTRA_PROJECT";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //mApiService = DI.getTaskApiService();
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

                //@Insert
                //long insertItem(Task task);

                Date dateOfCreation = Calendar.getInstance().getTime();

                //Task task = new Task(
                //        System.currentTimeMillis(),
                //        //R.color.colorTartampion,
                //        mAddTaskInput.getEditText().getText().toString().trim(),
                //        dateOfCreation
                //        //mSpinner.getSelectedItem().toString().trim()
                //);
                //mApiService.createTask(task);
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

    //public void init(){
    //    //mColor = taskSelection();
    //    Glide.with(getActivity())
    //            .load(R.drawable.lucidia)
    //            .into(mColor);
    //    mAddTaskInput.getEditText().addTextChangedListener(new TextWatcher() {
    //        @Override
    //        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    //        @Override
    //        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    //        @Override
    //        public void afterTextChanged(Editable s) {mAddBtn.setEnabled(s.length() > 0);}
    //    });
    //}

    //void taskColorFromItemSelection(){
    //    mSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //        @Override
    //        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //            switch (getId())
    //            {
    //                case 0:
    //                    mColor.setBackgroundColor(getResources().getColor(R.color.colorCircus));
    //                    break;
    //                case 1:
    //                    mColor.setBackgroundColor(getResources().getColor(R.color.colorLucidia));
    //                    break;
    //                case 2:
    //                    mColor.setBackgroundColor(getResources().getColor(R.color.colorTartampion));
    //                    break;
    //            }
    //        }
    //    });
    //}
}