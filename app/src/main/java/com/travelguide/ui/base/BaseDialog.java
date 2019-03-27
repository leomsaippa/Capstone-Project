package com.travelguide.ui.base;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import butterknife.Unbinder;

public class BaseDialog extends DialogFragment implements DialogMvpView {

    private  BaseActivity mActivity;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void showLoading(String title, String message) {
        if (mActivity != null) {
            mActivity.showLoading(title, message);
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

}
