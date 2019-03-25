package com.travelguide.data.network;

import com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult;

import io.reactivex.Observable;


public interface ApiHelper {

    void apiSetEndPoint(String endpoint);

    Observable<PlaceResult> apiGetPlaces(String query);
}
