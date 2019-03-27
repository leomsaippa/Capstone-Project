package com.travelguide.data.db;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper {

    @Inject
    public AppDbHelper(){
    }

    @Override
    public void createItinerary(String place) {

    }

}
