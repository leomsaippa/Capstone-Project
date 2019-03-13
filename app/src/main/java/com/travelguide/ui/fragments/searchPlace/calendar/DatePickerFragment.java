package com.travelguide.ui.fragments.searchPlace.calendar;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.travelguide.ui.base.BaseDialog;

public class DatePickerFragment extends BaseDialog  {

    public static final String TAG = DatePickerFragment.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return createCalendarDialog();

    }

}
