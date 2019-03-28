package com.travelguide.ui.fragments.itineraryList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelguide.R;
import com.travelguide.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ItineraryListFragment extends BaseFragment implements ItineraryListMvpView {


    @Inject
    ItineraryListMvpPresenter<ItineraryListMvpView> mPresenter;

    public static ItineraryListFragment getInstance() {
        Bundle args = new Bundle();
        ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
        itineraryListFragment.setArguments(args);
        return itineraryListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_itinerary_day,container,false);


        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        return view;

    }

}
