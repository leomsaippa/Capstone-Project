package com.travelguide.data.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelguide.data.network.model.Day;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DateConverter {

    @TypeConverter
    public static List<Day> stringToDay(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Day>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String daysListToString(List<Day> days) {
        Gson gson = new Gson();
        return gson.toJson(days);
    }
}