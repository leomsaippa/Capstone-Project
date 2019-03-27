package com.travelguide.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.travelguide.data.network.model.Itinerary;


@Database(entities = {Itinerary.class}, version = 1, exportSchema = false)
public abstract class ItineraryDbHelper extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "itinerary";
    private static ItineraryDbHelper instance;

    public static ItineraryDbHelper getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        ItineraryDbHelper.class, ItineraryDbHelper.DATABASE_NAME)
                        .build();
            }
        }
        return instance;
    }
    @Override
    public void clearAllTables() {

    }

    public abstract ItineraryDao itineraryDao();

}
