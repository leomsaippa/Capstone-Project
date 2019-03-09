package com.travelguide.di.component;

import dagger.Component;
import com.travelguide.di.PerActivity;
import com.travelguide.di.module.ActivityModule;
import com.travelguide.ui.main.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject (MainActivity mainActivity);
}
