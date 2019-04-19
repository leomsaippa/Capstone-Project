package com.travelguide.data.international;

import android.content.Context;

import javax.inject.Inject;

import com.travelguide.R;
import com.travelguide.di.ApplicationContext;
import com.travelguide.utils.AppConstants;
import com.travelguide.utils.CommonUtils;

import static com.travelguide.utils.AppConstants.API_PHOTO;
import static com.travelguide.utils.AppConstants.API_SENSOR;

public class AppStringHelper implements StringHelper {

    private final Context mContext;

    @Inject
    public AppStringHelper(@ApplicationContext Context context){
        this.mContext = context;
    }

    @Override
    public String getMessageLoading() {
        return mContext.getString(R.string.loading);
    }

    @Override
    public String generateTextPlaceQuery(String place) {
        String query = CommonUtils.mountCityQuery(place);
        return query + AppConstants.API_POINT_OF_INTEREST + mContext.getString(R.string.maps_apikey);
    }

    @Override
    public String generatePhotoQuery(String photoReference) {
        return API_PHOTO + photoReference + API_SENSOR + mContext.getString(R.string.maps_apikey);
    }


}
