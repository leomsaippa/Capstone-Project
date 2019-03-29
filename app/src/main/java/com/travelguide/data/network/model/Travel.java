package com.travelguide.data.network.model;

import java.util.Date;
import java.util.List;

public class Travel {

    String currentPlace ;
    Itinerary currentItinerary;

    long quantityDays = 0;

    Date dateBegin;
    Date dateEnd;

    List<Day> days;

    int countItineraries = 0;


    public Travel() {
    }

    public String getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(String currentPlace) {
        this.currentPlace = currentPlace;
    }

    public Itinerary getCurrentItinerary() {
        return currentItinerary;
    }

    public void setCurrentItinerary(Itinerary currentItinerary) {
        this.currentItinerary = currentItinerary;
    }

    public long getQuantityDays() {
        return quantityDays;
    }

    public void setQuantityDays(long quantityDays) {
        this.quantityDays = quantityDays;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public int getCountItineraries() {
        return countItineraries;
    }

    public void setCountItineraries(int countItineraries) {
        this.countItineraries = countItineraries;
    }
}
