package com.greg.todoc.picker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Pick {

    public static void pickDate(EditText mStartDateEdit, Context context){

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar cPicked = Calendar.getInstance();
                cPicked.set(Calendar.YEAR, year);
                cPicked.set(Calendar.MONTH, month);
                cPicked.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String pickedDate = simpleDateFormat.format(cPicked.getTime());

                if(mStartDateEdit.hasFocus())
                {
                    mStartDateEdit.setText(pickedDate);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
