package com.greg.todoc.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.greg.todoc.R;
import com.greg.todoc.events.FilterByDateEvent;
import com.greg.todoc.picker.Pick;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class DateDialog extends AppCompatDialogFragment {

    @BindView(R.id.dialogStartDateEdit) EditText mStartDateEdit;
    @BindView(R.id.dialogEndDateEdit) EditText mEndDateEdit;
    private Pick mPick;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_date_dialog, null);

        mStartDateEdit = view.findViewById(R.id.dialogStartDateEdit);
        mEndDateEdit = view.findViewById(R.id.dialogEndDateEdit);

        mStartDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pick.pickDate(mStartDateEdit, getActivity());
            }
        });

        mEndDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pick.pickDate(mEndDateEdit, getActivity());
            }
        });

        builder.setView(view)
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                             Date start = simpleDateFormat.parse(mStartDateEdit.getText().toString().trim());
                            Date end = simpleDateFormat.parse(mEndDateEdit.getText().toString().trim());
                            EventBus.getDefault().post(new FilterByDateEvent(start, end));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return builder.create();
    }
}