package com.travelguide.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;

import java.util.List;

@Dao
public interface ItineraryDao {

    @Query("SELECT * FROM itinerary")
    LiveData<List<Itinerary>> loadAllItineraries();

    @Query("SELECT * FROM itinerary WHERE id = :id")
    LiveData<Itinerary> getItinerary(int id);

    @Insert
    long insertItinerary(Itinerary itinerary);

    @Delete
    void deleteItinerary(Itinerary itinerary);

    @Update
    void update(Itinerary itinerary);
}
