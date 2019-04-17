package com.travelguide.data.sharedPreferences;

import com.travelguide.data.network.model.Day;

import java.util.List;

public interface SharedPref {

    void saveWidgetAttractions(List<Day> attractions);

    List<Day> loadWidgetAttraction();
}
