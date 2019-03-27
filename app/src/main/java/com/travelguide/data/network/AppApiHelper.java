package com.travelguide.data.network;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.travelguide.data.network.model.SearchPlaceResponse;

import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;


@Singleton
public class AppApiHelper implements ApiHelper {

    private String baseUrl;

    @Override
    public void apiSetEndPoint(String endpoint) {
        this.baseUrl = endpoint;
    }

    @Override
    public Observable<SearchPlaceResponse> apiGetPlaces(String query) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return Rx2AndroidNetworking.get(baseUrl + query)
                .setOkHttpClient(httpClient)
                .build()
                .getObjectObservable(SearchPlaceResponse.class);
    }
}
