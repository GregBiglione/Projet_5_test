package com.greg.todoc.dialog_box;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.greg.todoc.R;
import com.greg.todoc.events.FilterByProjectEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class ProjectDialog extends AppCompatDialogFragment {

    @BindView(R.id.projectDialogSpinner) Spinner mSpinner;
    @BindView(R.id.okProjectDialog) Button mOkProject;
    @BindView(R.id.cancelProjectDialog) Button mCancelProject;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_project_dialog, null);
        mSpinner = view.findViewById(R.id.projectDialogSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.project_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        builder.setView(view);

        mOkProject = view.findViewById(R.id.okProjectDialog);
        mOkProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String project = mSpinner.getSelectedItem().toString().trim();
                EventBus.getDefault().post(new FilterByProjectEvent(project));
                dismiss();
            }
        });

        mCancelProject = view.findViewById(R.id.cancelProjectDialog);
        mCancelProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }
}
