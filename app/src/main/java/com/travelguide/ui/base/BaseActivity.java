package com.travelguide.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.travelguide.di.component.ActivityComponent;
import com.travelguide.di.component.DaggerActivityComponent;
import com.travelguide.di.module.ActivityModule;

public class BaseActivity extends AppCompatActivity implements MvpView {

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


}
