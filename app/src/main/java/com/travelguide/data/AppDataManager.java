package com.travelguide.data;

import android.content.Context;

import javax.inject.Inject;

import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.data.prefs.PreferencesHelper;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

public class AppDataManager implements DataManager{

    DbHelper mDbHelper;
    StringHelper mStringHelper;
    ApiHelper mApiHelper;
    PreferencesHelper mPreferencesHelper;

    String place = "";
    long quantityDays = 0;
    Date dateBegin;
    Date dateEnd;
    List<Day> days;

    int numberItineraries = 0;

    Itinerary itinerary;
    @Inject
    public AppDataManager(DbHelper dbHelper, StringHelper stringHelper,
                          ApiHelper apiHelper, PreferencesHelper preferencesHelper){

        this.mDbHelper = dbHelper;
        this.mStringHelper = stringHelper;
        this.mApiHelper = apiHelper;
        this.mPreferencesHelper = preferencesHelper;
    }


    @Override
    public void openMainActivity() {

    }

    @Override
    public String generateQuery(String place) {
        return mStringHelper.generateQuery(place);
    }


    @Override
    public void setCurrentPlace(String place) {
        this.place = place;
    }

    @Override
    public void apiSetEndPoint(String endpoint) {
        mApiHelper.apiSetEndPoint(endpoint);
    }

    @Override
    public Observable<SearchPlaceResponse> apiGetPlaces(String query) {
        return mApiHelper.apiGetPlaces(query);
    }

    @Override
    public String getMessageLoading() {
        return mStringHelper.getMessageLoading();
    }


    @Override
    public String getCurrentPlace() {
        return place;
    }

    @Override
    public void addAttraction(String name, Date date) {
        long diff  = date.getTime() - dateBegin.getTime();
        days.get((int) diff).getAttractions().add(name);

    }


    @Override
    public void setQuantityDays(long quantityDays) {
        this.quantityDays = quantityDays;
        for(int i=0;i<quantityDays;i++){
            Day day = new Day(i);
            days.add(day);
        }
    }

    @Override
    public void setDateBeginTravel(Date dateBeginTravel) {
        dateBegin = dateBeginTravel;
    }

    @Override
    public void setEndTravel(Date dateEndTravel) {
        dateEnd = dateEndTravel;
    }

    @Override
    public void onConfirmItinerary(String currentPlace, Context mContext) {
        numberItineraries++;
        itinerary = new Itinerary(numberItineraries,currentPlace,days.size(),days);
        mDbHelper.createItinerary(itinerary,mContext);

    }

    @Override
    public void createItinerary(Itinerary itinerary, Context mContext) {
        mDbHelper.createItinerary(itinerary,mContext);
    }
}
