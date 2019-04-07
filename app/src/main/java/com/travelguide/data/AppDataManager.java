package com.travelguide.data;


import javax.inject.Inject;

import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.data.prefs.PreferencesHelper;

import org.joda.time.LocalDate;

import java.util.List;

import io.reactivex.Observable;

public class AppDataManager implements DataManager{

    DbHelper mDbHelper;
    StringHelper mStringHelper;
    ApiHelper mApiHelper;
    PreferencesHelper mPreferencesHelper;

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
    public void addAttraction(String name, LocalDate date) {
        mDbHelper.addAttraction(name,date);
    }


    @Override
    public void onConfirmItinerary(String currentPlace) {
        mDbHelper.onConfirmItinerary(currentPlace);
    }

    @Override
    public Itinerary createItinerary(String place, int quantityDays, LocalDate dateBeginTravel,
                                     LocalDate dateEndTravel, List<Day> days) {
        return mDbHelper.createItinerary(place,quantityDays,dateBeginTravel,dateEndTravel,days);
    }

    @Override
    public void updateItinerary(Itinerary itinerary) {
        mDbHelper.updateItinerary(itinerary);
    }
}
