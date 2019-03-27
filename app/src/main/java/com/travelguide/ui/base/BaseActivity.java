package com.travelguide.ui.base;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;

import com.travelguide.di.component.ActivityComponent;
import com.travelguide.di.component.DaggerActivityComponent;
import com.travelguide.di.module.ActivityModule;
import com.travelguide.utils.CommonUtils;

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
}
