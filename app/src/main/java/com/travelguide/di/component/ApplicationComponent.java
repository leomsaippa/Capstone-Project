package com.travelguide.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.travelguide.MoviesListApplication;
import com.travelguide.data.DataManager;
import com.travelguide.di.ApplicationContext;
import com.travelguide.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MoviesListApplication app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();


}
