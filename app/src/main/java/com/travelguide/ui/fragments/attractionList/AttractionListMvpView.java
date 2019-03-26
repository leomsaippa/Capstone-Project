package com.travelguide.ui.fragments.attractionList;

import com.travelguide.data.network.model.PlaceResult;
import com.travelguide.ui.base.MvpView;

public interface AttractionListMvpView extends MvpView {

    void openAttractionDetailFragment(PlaceResult searchPlaceResult);
}
