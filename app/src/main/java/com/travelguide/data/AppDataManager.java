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
    public String generateTextPlaceQuery(String place) {
        return mStringHelper.generateTextPlaceQuery(place);
    }

    @Override
    public String generatePhotoQuery(String photoReference) {
        return mStringHelper.generatePhotoQuery(photoReference);
    }

    @Override
    public String generateTextCityQuery(String city) {
        return mStringHelper.generateTextCityQuery(city);
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
    public Itinerary createItinerary(String place, int quantityDays, LocalDate dateBeginTravel,
                                     LocalDate dateEndTravel, List<Day> days, String photo_reference) {
        return mDbHelper.createItinerary(place,quantityDays,dateBeginTravel,dateEndTravel,days, photo_reference);
    }

    @Override
    public void updateItinerary(Itinerary itinerary) {
        mDbHelper.updateItinerary(itinerary);
    }
}
