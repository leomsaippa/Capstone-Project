package com.travelguide.ui.fragments.searchPlace;

import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.ui.base.MvpView;

public interface SearchPlaceMvpView extends MvpView {

    void openAttractionListFragment(SearchPlaceResponse placeResponse, Itinerary itinerary);

    void onErrorEmptyPlace();

    void onErrorEmptyBeginTravel();

    void onErrorEmptyEndTravel();

    void onErrorInvalidDate();

    void search(String photo_reference);

    void showInternetError();

    void showZeroResultError();
}
