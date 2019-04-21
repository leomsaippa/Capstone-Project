package com.travelguide.ui.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.travelguide.di.component.ActivityComponent;
import com.travelguide.di.component.DaggerActivityComponent;
import com.travelguide.di.module.ActivityModule;
import com.travelguide.utils.CommonUtils;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements MvpView {

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((BaseApplication) getApplication()).getComponent())
                .build();

    }


    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showLoading(String title, String message) {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this, title, message);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showAlertMessage(String message, String textPositiveButton,
                                 DialogInterface.OnClickListener onClickListenerPositive,
                                 String textNegativeButton,
                                 DialogInterface.OnClickListener onClickListenerNegative) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(textPositiveButton, onClickListenerPositive);
        builder.setNegativeButton(textNegativeButton, onClickListenerNegative);
        builder.setCancelable(false);
        builder.create().show();
    }



}
