package com.travelguide.ui.fragments.itineraryDetail;

import android.os.Bundle;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.itineraryList.ItineraryListMvpView;

public class ItineraryDetailFragment extends BaseFragment implements ItineraryListMvpView {

    public static final String TAG = ItineraryDetailFragment.class.getSimpleName();

    public static ItineraryDetailFragment getInstance(Itinerary itinerary) {
        Bundle args = new Bundle();
        ItineraryDetailFragment itineraryDetailFragment = new ItineraryDetailFragment();
        itineraryDetailFragment.setArguments(args);
        return itineraryDetailFragment;
    }
}
