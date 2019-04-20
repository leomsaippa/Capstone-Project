package com.travelguide.data;


import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.prefs.PreferencesHelper;

import org.joda.time.LocalDate;

import java.util.List;

public interface DataManager extends DbHelper, StringHelper, ApiHelper, PreferencesHelper {

    void openMainActivity();

    String generateTextPlaceQuery(String place);

    Itinerary createItinerary(String place, int quantityDays, LocalDate dateBeginTravel, LocalDate dateEndTravel, List<Day> days, String photo_reference);

    void updateItinerary(Itinerary itinerary);

}
