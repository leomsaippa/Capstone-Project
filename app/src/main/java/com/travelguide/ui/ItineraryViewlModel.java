package com.travelguide.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.travelguide.data.db.ItineraryDbHelper;
import com.travelguide.data.network.model.Itinerary;

import java.util.List;

public class ItineraryViewlModel extends AndroidViewModel {

    final LiveData<List<Itinerary>> mItineraries;

    public ItineraryViewlModel(@NonNull Application application) {
        super(application);
        mItineraries = ItineraryDbHelper.getInstance(application.getApplicationContext()).itineraryDao().loadAllItineraries();
    }

    public LiveData<List<Itinerary>> getItineraries() {
        return mItineraries;
    }
}
