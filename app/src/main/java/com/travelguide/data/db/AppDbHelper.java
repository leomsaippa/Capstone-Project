package com.travelguide.data.db;

import android.content.Context;

import com.travelguide.data.network.model.Itinerary;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper {

    @Inject
    public AppDbHelper(){
    }

    @Override
    public void createItinerary(Itinerary itinerary, Context context) {
        ItineraryDbHelper itineraryDbHelper = ItineraryDbHelper.getInstance(context);
        itineraryDbHelper.itineraryDao().insertItinerary(itinerary);
    }

}
