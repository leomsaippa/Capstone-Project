package com.travelguide.data;

import android.content.Context;

import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.prefs.PreferencesHelper;

import java.util.Date;

public interface DataManager extends DbHelper, StringHelper, ApiHelper, PreferencesHelper {

    void openMainActivity();

    String generateQuery(String place);

    void setCurrentPlace(String place);

    String getCurrentPlace();

    void addAttraction(String name, Date date);

    void setQuantityDays(long quantityDays);

    void setDateBeginTravel(Date dateBeginTravel);

    void setEndTravel(Date dateEndTravel);

    void onConfirmItinerary(String currentPlace, Context context);
}
