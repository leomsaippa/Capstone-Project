package com.travelguide.data.db;

import java.util.Date;

public interface DbHelper {

    void setCurrentPlace(String place);

    String getCurrentPlace();

    void addAttraction(String name, Date date);

    void setQuantityDays(long quantityDays);

    void setDateBeginTravel(Date dateBeginTravel);

    void setDateEndTravel(Date dateEndTravel);

    void onConfirmItinerary(String place);
}
