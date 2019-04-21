package com.travelguide.ui.fragments.attractionDetail;

import com.travelguide.data.network.model.Itinerary;
import com.travelguide.ui.base.MvpPresenter;

public interface AttractionDetailMvpPresenter<V extends AttractionDetailMvpView> extends MvpPresenter<V> {

    void updateItinerary(Itinerary itinerary);

    String generatePhotoQuery(String photoReference);
}
