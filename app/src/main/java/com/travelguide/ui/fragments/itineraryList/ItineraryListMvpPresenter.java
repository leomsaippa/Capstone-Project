package com.travelguide.ui.fragments.itineraryList;

import com.travelguide.ui.base.MvpPresenter;

public interface ItineraryListMvpPresenter<V extends ItineraryListMvpView> extends MvpPresenter<V> {

    void onBtnLoadItinerariesClick();

    String generateCityQuery(String place);

}
