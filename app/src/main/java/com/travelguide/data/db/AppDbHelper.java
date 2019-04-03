package com.travelguide.data.db;

import android.content.Context;

import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.Travel;
import com.travelguide.di.ApplicationContext;
import com.travelguide.utils.AppExecutors;

import org.joda.time.DateTimeComparator;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper {

    private final Context mContext;

    private Travel mTravel;

    @Inject
    public AppDbHelper(@ApplicationContext Context context){
        this.mContext = context;
        mTravel = new Travel();
    }


    public void createItinerary(Itinerary itinerary) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                ItineraryDbHelper itineraryDbHelper = ItineraryDbHelper.getInstance(mContext);
                itineraryDbHelper.itineraryDao().insertItinerary(itinerary);
            }
        });

    }

    @Override
    public void setCurrentPlace(String currentPlace) {
        mTravel.setCurrentPlace(currentPlace);

    }

    @Override
    public String getCurrentPlace() {
        return mTravel.getCurrentPlace();
    }

    @Override
    public void addAttraction(String name, LocalDate date) {
        int quantityDays = Days.daysBetween(mTravel.getDateBegin(), date).getDays();
        //todo get and asserts not null
        List<String> attractions = new ArrayList<>();
        attractions.add(name);
        mTravel.getDays().get(quantityDays).setAttractions(attractions);
    }

    @Override
    public void setQuantityDays(long quantityDays) {
        List<Day> days = new ArrayList<>();
        for(int i=0;i<quantityDays;i++){
            Day day = new Day(i);
            days.add(day);
        }
        mTravel.setDays(days);

    }

    @Override
    public void setDateBeginTravel(LocalDate dateBeginTravel) {
        mTravel.setDateBegin(dateBeginTravel);
    }

    @Override
    public void setDateEndTravel(LocalDate dateEndTravel) {
        mTravel.setDateEnd(dateEndTravel);
    }

    @Override
    public void onConfirmItinerary(String place) {
        int count = mTravel.getCountItineraries();
        mTravel.setCountItineraries(count++);
        Itinerary itinerary = new Itinerary(Integer.valueOf(987654321),
                mTravel.getCurrentPlace(),
                mTravel.getDays().size(),
                mTravel.getDays());

        createItinerary(itinerary);
    }
}
