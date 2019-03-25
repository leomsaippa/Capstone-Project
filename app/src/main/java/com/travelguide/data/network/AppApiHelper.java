package com.travelguide.data.network;

import com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class AppApiHelper implements ApiHelper {

    private String baseUrl;

    @Override
    public void apiSetEndPoint(String endpoint) {
        this.baseUrl = endpoint;
    }

    @Override
    public Observable<PlaceResult> apiGetPlaces(String query) {
/*

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new HttpLoggingInterceptor());
*/


        return Rx2AndroidNetworking.get(baseUrl + query)
//                .setOkHttpClient(httpClient)
                .build()
                .getObjectObservable(PlaceResult.class);
    }
}
