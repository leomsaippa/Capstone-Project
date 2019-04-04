package com.travelguide.data.db;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.Travel;
import com.travelguide.di.ApplicationContext;
import com.travelguide.utils.AppExecutors;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper {

    private final Context mContext;

    private long currentId = 0;

    ItineraryDbHelper itineraryDbHelper;

    @Inject
    public AppDbHelper(@ApplicationContext Context context){
        this.mContext = context;
        itineraryDbHelper = ItineraryDbHelper.getInstance(mContext);
    }

    @Override
    public void addAttraction(String name, LocalDate date) {
        Integer id = (int) (long) currentId;
        final LiveData<Itinerary> itinerary = itineraryDbHelper.itineraryDao().getItinerary(id);

        if(itinerary.getValue() !=null){
            LocalDate dayBegin = itinerary.getValue().getDayBegin();
            int quantityDays = Days.daysBetween(dayBegin, date).getDays();
            List<String> attractions = itinerary.getValue().getList_days().get(quantityDays).getAttractions();
            attractions.add(name);
            itinerary.getValue().getList_days().get(quantityDays).setAttractions(attractions);

        }
    }

    @Override
    public void onConfirmItinerary(String place) {

    }

    @Override
    public void createItinerary(String place, int quantityDays, LocalDate dateBeginTravel, LocalDate dateEndTravel, List<Day> days) {
        Itinerary itinerary = new Itinerary(place, quantityDays, dateBeginTravel, dateEndTravel, days);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                currentId = itineraryDbHelper.itineraryDao().insertItinerary(itinerary);
            }
        });
    }
}
