package com.travelguide.data.international;

import android.content.Context;

import javax.inject.Inject;

import com.travelguide.di.ApplicationContext;

public class AppStringHelper implements StringHelper {

    private final Context mContext;

    @Inject
    public AppStringHelper(@ApplicationContext Context context){
        this.mContext = context;
    }

    //Adds information about string
}
