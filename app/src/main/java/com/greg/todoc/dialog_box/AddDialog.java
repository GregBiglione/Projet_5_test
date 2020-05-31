package com.greg.todoc.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.greg.todoc.R;
import com.greg.todoc.di.DI;
import com.greg.todoc.model.Task;
import com.greg.todoc.service.TaskApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddDialog extends AppCompatDialogFragment {

    private TaskApiService mApiService;
    @BindView(R.id.imageColor) CircleImageView mColor;
    @BindView(R.id.addTaskInput) TextInputLayout mAddTaskInput;
    @BindView(R.id.addTaskEt) EditText mAddTitle;
    @BindView(R.id.dateOfCreationInput) TextInputLayout mDateInput;
    @BindView(R.id.DateEt) EditText mAddDate;
    @BindView(R.id.projectSpinner) Spinner mSpinner;


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

        builder.setView(view)
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
