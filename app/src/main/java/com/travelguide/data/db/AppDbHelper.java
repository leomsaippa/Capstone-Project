package com.travelguide.data.db;

import android.content.Context;
import android.util.Log;

import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.di.ApplicationContext;
import com.travelguide.utils.AppExecutors;

import org.joda.time.LocalDate;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper {

    public static final String TAG = AppDbHelper.class.getSimpleName();

    private final Context mContext;

    private long currentId = -1;

    ItineraryDbHelper itineraryDbHelper;

    @Inject
    public AppDbHelper(@ApplicationContext Context context){
        this.mContext = context;
        itineraryDbHelper = ItineraryDbHelper.getInstance(mContext);
    }

    @Override
    public Itinerary createItinerary(String place, int quantityDays, LocalDate dateBeginTravel, LocalDate dateEndTravel, List<Day> days, String photo_reference) {
        Itinerary itinerary = new Itinerary(place, quantityDays, dateBeginTravel, dateEndTravel, days, photo_reference);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                currentId = itineraryDbHelper.itineraryDao().insertItinerary(itinerary);
                Log.d(TAG, "Current id " +itinerary.getId());

            }
        });

        while (currentId==-1){

        }
        Integer id = (int) (long) currentId;
        itinerary.setId(id);
        return itinerary;
    }

    @Override
    public void updateItinerary(Itinerary itinerary) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                Log.d(TAG, "Current id " +itinerary.getId());
                itineraryDbHelper.itineraryDao().update(itinerary);
                Log.d(TAG,"Itinerary update successfully ");

            }
        });

    }
}
