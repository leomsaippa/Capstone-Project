package com.travelguide.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import com.travelguide.di.ActivityContext;
import com.travelguide.di.PerActivity;
import com.travelguide.ui.main.MainMvpPresenter;
import com.travelguide.ui.main.MainMvpView;
import com.travelguide.ui.main.MainPresenter;
import com.travelguide.utils.rx.AppSchedulerProvider;
import com.travelguide.utils.rx.SchedulerProvider;

@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity){
        this.mActivity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> providesMainPresenter(
            MainPresenter<MainMvpView> presenter){
        return presenter;
    }


}
