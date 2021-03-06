package com.travelguide.data.network;

import android.util.Log;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.travelguide.data.network.model.SearchPlaceResponse;

import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;

import static com.travelguide.utils.AppConstants.API_TEXT_SEARCH;

@Singleton
public class AppApiHelper implements ApiHelper {

    public static final String TAG = AppApiHelper.class.getSimpleName();

    private String baseUrl;

    @Override
    public void apiSetEndPoint(String endpoint) {
        this.baseUrl = endpoint;
    }

    @Override
    public Observable<SearchPlaceResponse> apiGetPlaces(String query) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Log.d(TAG,"Query: " + baseUrl + query);

        return Rx2AndroidNetworking.get(baseUrl + API_TEXT_SEARCH + query)
                .setOkHttpClient(httpClient)
                .build()
                .getObjectObservable(SearchPlaceResponse.class);
    }
}
