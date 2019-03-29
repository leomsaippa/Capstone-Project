package com.travelguide.ui.fragments.itineraryList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelguide.R;
import com.travelguide.data.db.ItineraryDbHelper;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.ui.ItineraryViewlModel;
import com.travelguide.ui.base.BaseFragment;


import javax.inject.Inject;

import butterknife.ButterKnife;

public class ItineraryListFragment extends BaseFragment implements ItineraryListMvpView {


    public static final String TAG = ItineraryListFragment.class.getSimpleName();

    private ItineraryViewlModel itinerariesViewModel;

    ItineraryDbHelper mDb;

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

        setupViewModel();

        mDb = ItineraryDbHelper.getInstance(getContext());

        loadItineraries();

        return view;

    }

    private void loadItineraries() {
        itinerariesViewModel.getItineraries().observe(this,
                itineraries -> Log.d(TAG,"Load itineraries " + itineraries.get(0).getName()));
    }


    private void setupViewModel() {

        itinerariesViewModel = ViewModelProviders.of(this)
                .get(ItineraryViewlModel.class);
    }

    private void removeObservers() {
        if ( itinerariesViewModel != null && itinerariesViewModel.getItineraries().hasObservers()){
            itinerariesViewModel.getItineraries().removeObservers(this);
        }
    }

    private void loadItinerary(int id) {
        final LiveData<Itinerary> itinerary = mDb.itineraryDao().getItinerary(id);
        itinerary.observe(this, new Observer<Itinerary>() {
            @Override
            public void onChanged(@Nullable Itinerary it) {
                itinerary.removeObserver(this);
                Log.d(TAG, "CheckFavorite " + it.getName());

            }
        });
    }
}
