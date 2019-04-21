package com.travelguide.ui.fragments.searchPlace.calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.DatePicker;

import com.travelguide.ui.base.BaseDialog;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends BaseDialog implements DatePickerDialog.OnDateSetListener  {

    public static final String TAG = DatePickerFragment.class.getSimpleName();

    private OnSelectedDate dateSelectedCallback;

    public interface OnSelectedDate{
        void onSelectedDate(int year, int month, int dayOfMonth);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dateSelectedCallback = (OnSelectedDate) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement Callback interface");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(Objects.requireNonNull(getContext()), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateSelectedCallback.onSelectedDate(year, month, dayOfMonth);
    }
}
