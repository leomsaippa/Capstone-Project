package com.travelguide.data.db;

import android.content.Context;

import com.travelguide.data.network.model.Itinerary;

public interface DbHelper {

    void createItinerary(Itinerary itinerary, Context mContext);
}
