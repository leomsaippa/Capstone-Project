package com.travelguide.data.network;

import com.travelguide.data.network.model.SearchPlaceResponse;

import io.reactivex.Observable;


public interface ApiHelper {

    void apiSetEndPoint(String endpoint);

    Observable<SearchPlaceResponse> apiGetPlaces(String query);
}
