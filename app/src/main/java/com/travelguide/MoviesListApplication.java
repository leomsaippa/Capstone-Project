package com.travelguide;

import android.app.Application;

import javax.inject.Inject;

import com.travelguide.data.DataManager;
import com.travelguide.di.component.ApplicationComponent;
import com.travelguide.di.component.DaggerApplicationComponent;
import com.travelguide.di.module.ApplicationModule;
import com.travelguide.ui.base.BaseApplication;

public class MoviesListApplication extends Application implements BaseApplication {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;



    @Override
    public void onCreate() {
        super.onCreate();

        if(mApplicationComponent == null){
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
            mApplicationComponent.inject(MoviesListApplication.this);
        }

    }

    @Override
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
