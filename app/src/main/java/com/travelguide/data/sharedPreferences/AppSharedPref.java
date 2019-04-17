package com.travelguide.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelguide.data.network.model.Day;

import java.lang.reflect.Type;
import java.util.List;

public class AppSharedPref implements SharedPref {

    public static final String DAYS_PARAM = "Days";
    Context context;
    final SharedPreferences sharedPreferences;
    private final Gson gson;

    public AppSharedPref(Context context){
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }


    @Override
    public void saveWidgetAttractions(List<Day> days) {
        String dayJson = gson.toJson(days);
        sharedPreferences.getString(DAYS_PARAM,dayJson);
        sharedPreferences.edit().putString(DAYS_PARAM, days.toString()).apply();
    }

    @Override
    public List<Day> loadWidgetAttraction() {
        String jsonPreferences = sharedPreferences.getString(DAYS_PARAM, "");

        Type type = new TypeToken<List<Day>>() {}.getType();
        return gson.fromJson(jsonPreferences, type);
    }
}
