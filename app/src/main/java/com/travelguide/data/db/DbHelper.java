package com.travelguide.data.db;

import org.joda.time.LocalDate;

import java.text.DateFormat;

public interface DbHelper {

    void setCurrentPlace(String place);

    String getCurrentPlace();

    void addAttraction(String name, LocalDate date);

    void setQuantityDays(long quantityDays);

    void setDateBeginTravel(LocalDate dateBeginTravel);

    void setDateEndTravel(LocalDate dateEndTravel);

    void onConfirmItinerary(String place);
}
