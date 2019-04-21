package com.travelguide.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.travelguide.R;
import com.travelguide.data.network.model.Day;


import java.util.ArrayList;
import java.util.List;

import static com.travelguide.utils.AppConstants.API_QUERY;

public class CommonUtils {

    public static ProgressDialog showLoadingDialog(Context context,
                                                   String title,
                                                   String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (title != null) {
            progressDialog.setTitle(title);
        }
        if (message != null) {
            progressDialog.setMessage(message);
        }
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static String mountCityQuery(String place) {
        place = place.replace(" ", "+");
        return API_QUERY + place;
    }

    public static List<Day> createDays(int quantityDays){
        List<Day> days = new ArrayList<>();
        for(int i=0;i<quantityDays;i++){
            Day day = new Day(i);
            days.add(day);
        }
        return days;
    }
}
