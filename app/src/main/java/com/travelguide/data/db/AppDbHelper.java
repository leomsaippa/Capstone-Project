package com.travelguide.data.db;

import android.content.Context;

import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.Travel;
import com.travelguide.di.ApplicationContext;

import java.util.Date;

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
        ItineraryDbHelper itineraryDbHelper = ItineraryDbHelper.getInstance(mContext);
        itineraryDbHelper.itineraryDao().insertItinerary(itinerary);
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
    public void addAttraction(String name, Date date) {
        long diff  = date.getTime() - mTravel.getDateBegin().getTime();
        mTravel.getDays().get((int) diff).getAttractions().add(name);
    }

    @Override
    public void setQuantityDays(long quantityDays) {
        for(int i=0;i<quantityDays;i++){
            Day day = new Day(i);
            mTravel.getDays().add(day);
        }
    }

    @Override
    public void setDateBeginTravel(Date dateBeginTravel) {
        mTravel.setDateBegin(dateBeginTravel);
    }

    @Override
    public void setDateEndTravel(Date dateEndTravel) {
        mTravel.setDateEnd(dateEndTravel);
    }

    @Override
    public void onConfirmItinerary(String place) {
        int count = mTravel.getCountItineraries();
        mTravel.setCountItineraries(count++);
        Itinerary itinerary = new Itinerary(mTravel.getCountItineraries(),
                mTravel.getCurrentPlace(),
                mTravel.getDays().size(),
                mTravel.getDays());

        createItinerary(itinerary);
    }
}
