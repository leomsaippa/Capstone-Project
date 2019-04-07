package com.travelguide.data.db;

import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;

import org.joda.time.LocalDate;

import java.util.List;

public interface DbHelper {

    void addAttraction(String name, LocalDate date);

    void onConfirmItinerary(String place);

    Itinerary createItinerary(String place, int quantityDays, LocalDate dateBeginTravel, LocalDate dateEndTravel, List<Day> days);

    void updateItinerary(Itinerary itinerary);
}
