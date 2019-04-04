package com.travelguide.data.db;

import com.travelguide.data.network.model.Day;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.util.List;

public interface DbHelper {

    void addAttraction(String name, LocalDate date);

    void onConfirmItinerary(String place);

    void createItinerary(String place, int quantityDays, LocalDate dateBeginTravel, LocalDate dateEndTravel, List<Day> days);
}
