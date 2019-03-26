package com.travelguide.ui.fragments.searchPlace;

import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.ui.base.MvpView;

public interface SearchPlaceMvpView extends MvpView {

    void openAttractionListFragment(SearchPlaceResponse placeResponse);

    void onErrorEmptyPlace();
}
