package com.travelguide.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.travelguide.data.network.model.Itinerary;

import java.util.List;

@Dao
public interface ItineraryDao {

    @Query("SELECT * FROM itinerary")
    LiveData<List<Itinerary>> loadAllItineraries();

    @Query("SELECT * FROM itinerary WHERE id = :itineraryId")
    LiveData<Itinerary> getItinerary(int itineraryId);

    @Insert
    void insertItinerary(Itinerary itinerary);

    @Delete
    void deleteItinerary(Itinerary itinerary);
}
