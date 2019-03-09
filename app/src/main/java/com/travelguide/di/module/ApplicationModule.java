package com.travelguide.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.travelguide.data.AppDataManager;
import com.travelguide.data.DataManager;
import com.travelguide.data.android.AndroidResolver;
import com.travelguide.data.android.Resolver;
import com.travelguide.data.db.AppDbHelper;
import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.AppStringHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.network.AppApiHelper;
import com.travelguide.data.prefs.AppPreferencesHelper;
import com.travelguide.data.prefs.PreferencesHelper;
import com.travelguide.di.ApplicationContext;
import com.travelguide.di.PreferenceInfo;
import com.travelguide.utils.AppConstants;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application){
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        return mApplication;
    }


    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    ApiHelper provideApiHelper() {
        return new AppApiHelper();
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    StringHelper providesStringHelper(AppStringHelper appStringHelper) {
        return appStringHelper;
    }

    @Provides
    @Singleton
    Resolver provideAndroidResolver(AndroidResolver androidResolver) {
        return androidResolver;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper (AppDbHelper appDbHelper){
        return appDbHelper;
    }




}
