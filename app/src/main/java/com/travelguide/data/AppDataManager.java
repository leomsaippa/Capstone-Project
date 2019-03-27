package com.travelguide.data;

import javax.inject.Inject;

import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.data.prefs.PreferencesHelper;

import io.reactivex.Observable;

public class AppDataManager implements DataManager{

    DbHelper mDbHelper;
    StringHelper mStringHelper;
    ApiHelper mApiHelper;
    PreferencesHelper mPreferencesHelper;

    String place = "";

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
    public void createItinerary(String place) {
        mDbHelper.createItinerary(place);
    }

    @Override
    public String getPlace() {
        return place;
    }
}
