package com.travelguide.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.travelguide.R;
import com.travelguide.ui.base.BaseActivity;

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
}
