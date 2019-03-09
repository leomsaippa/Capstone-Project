package com.travelguide.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.travelguide.di.ApplicationContext;
import com.travelguide.di.PreferenceInfo;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName){
        this.mPrefs = context.getSharedPreferences(prefFileName,Context.MODE_PRIVATE);
    }

    //Adds information about user preferences
}
