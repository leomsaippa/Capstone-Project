package com.travelguide.data;

import com.travelguide.data.db.DbHelper;
import com.travelguide.data.international.StringHelper;
import com.travelguide.data.network.ApiHelper;
import com.travelguide.data.prefs.PreferencesHelper;

public interface DataManager extends DbHelper, StringHelper, ApiHelper, PreferencesHelper {

    void openMainActivity();
}
