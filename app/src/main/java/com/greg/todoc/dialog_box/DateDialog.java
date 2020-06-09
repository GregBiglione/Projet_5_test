package com.greg.todoc.dialog_box;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
    @BindView(R.id.okDateDialog) Button mOkDate;
    @BindView(R.id.cancelDateDialog) Button mCanceldate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_date_dialog, null);

        mStartDateEdit = view.findViewById(R.id.dialogStartDateEdit);
        mEndDateEdit = view.findViewById(R.id.dialogEndDateEdit);

        mOkDate = view.findViewById(R.id.okDateDialog);
        mOkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDate = mStartDateEdit.getText().toString().trim();
                String endDate = mEndDateEdit.getText().toString().trim();
                if (endDate.compareTo(startDate) < 0)
                {
                    mEndDateEdit.setError("Date de fin incorrecte");
                }
                else
                {
                    mEndDateEdit.setText(endDate);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date start = simpleDateFormat.parse(startDate);
                        Date end = simpleDateFormat.parse(endDate);
                        EventBus.getDefault().post(new FilterByDateEvent(start, end));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    dismiss();
                }
            }
        });

        mCanceldate = view.findViewById(R.id.cancelDateDialog);
        mCanceldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

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

        builder.setView(view);
        return builder.create();
    }
}
