package com.greg.todoc.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.greg.todoc.R;

import butterknife.BindView;

public class DateDialog extends AppCompatDialogFragment {

    @BindView(R.id.dialogStartDateLyt) TextInputLayout mStartDateInput;
    @BindView(R.id.dialogStartDateEdit) EditText mStartDateEdit;
    @BindView(R.id.dialogEndDateLyt) TextInputLayout mEndDateInput;
    @BindView(R.id.dialogEndDateEdit) EditText mEndDateEdit;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_date_dialog, null);
        mStartDateEdit = view.findViewById(R.id.dialogStartDateEdit);
        mEndDateEdit = view.findViewById(R.id.dialogEndDateEdit);
        builder.setView(view)
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
