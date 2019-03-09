package com.travelguide.data.android;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.travelguide.di.ApplicationContext;

@Singleton
public class AndroidResolver implements Resolver {

    private final Context mContext;

    @Inject
    public AndroidResolver(@ApplicationContext Context context){
        mContext = context;
    }

    //Adds information about user Android


}
