package com.travelguide.data;

import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.prefs.PreferencesHelper;

import org.joda.time.LocalDate;

import java.text.DateFormat;

public interface DataManager extends DbHelper, StringHelper, ApiHelper, PreferencesHelper {

    void openMainActivity();

    String generateQuery(String place);

    void setCurrentPlace(String place);

    String getCurrentPlace();

    void addAttraction(String name, LocalDate date);

    void setQuantityDays(long quantityDays);

    void setDateBeginTravel(LocalDate dateBeginTravel);

    void setDateEndTravel(LocalDate dateEndTravel);

    void onConfirmItinerary(String currentPlace);
}
