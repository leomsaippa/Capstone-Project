package com.travelguide.ui.fragments.itineraryList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelguide.R;
import com.travelguide.data.db.ItineraryDbHelper;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.ui.ItineraryViewlModel;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.itineraryDetail.ItineraryDetailFragment;
import com.travelguide.utils.EndlessRecyclerViewScrollListener;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItineraryListFragment extends BaseFragment implements ItineraryListMvpView, ItineraryAdapter.ItineraryAdapterOnClickerHandler {


    public static final String TAG = ItineraryListFragment.class.getSimpleName();

    private EndlessRecyclerViewScrollListener scrollListener;

    private ItineraryAdapter mAdapter;

    @BindView(R.id.rv_itinerary_list)
    RecyclerView mRecyclerView;

    GridLayoutManager layoutManager;

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

        View view = inflater.inflate(R.layout.frag_itinerary_list,container,false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        setupViewModel();

        mDb = ItineraryDbHelper.getInstance(getContext());

        loadItineraries();

        layoutManager = new GridLayoutManager(getContext(),1);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItineraryAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // mRecyclerView.addOnScrollListener(scrollListener);

        return view;

    }

    private void loadItineraries() {
        itinerariesViewModel.getItineraries().observe(this, itineraries -> {
            if(itineraries!=null) {
                for (int i = 0; i < itineraries.size(); i++) {
                    Log.d(TAG, "Day "+ i + " lista " + itineraries.get(i).getList_days());
                }
            }
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();
            mAdapter.setItineraryList(itineraries);
            showList();
            mAdapter.notifyDataSetChanged();
        });
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
                Log.d(TAG, "Load itinerary " + Objects.requireNonNull(it).getName());

            }
        });
    }

    @Override
    public void onCLick(Itinerary itinerary) {
        Log.d(TAG,"Itinerary " + itinerary.getName());
        loadItinerary(itinerary.getId());
        openItineraryDetailFragment(itinerary);
    }

    private void openItineraryDetailFragment(Itinerary itinerary) {
        if(getActivity() != null){
            if(getActivity().getSupportFragmentManager() != null){

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, ItineraryDetailFragment.getInstance(itinerary), ItineraryDetailFragment.TAG)
                        .addToBackStack(ItineraryDetailFragment.TAG)
                        .commit();

            }
        }
    }



    private void loadMoreItineraries(int currentPage) {
        //TODO adds logic to get more response from db
        mPresenter.onBtnLoadItinerariesClick();
    }

    private void showList(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeObservers();
    }

}
