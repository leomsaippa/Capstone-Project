package com.travelguide.ui.fragments.itineraryDay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.travelguide.R;
import com.travelguide.data.network.model.Day;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.dayRoute.DayRouteFragment;
import com.travelguide.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItineraryDayFragment extends BaseFragment implements ItineraryDayMvpView, ItineraryDayAdapter.ItineraryDayAdapterOnClickHandler {

    public static final String PARAM_DAY = "DAY";
    public static final String TAG = ItineraryDayFragment.class.getSimpleName();

    private Day day;

    private EndlessRecyclerViewScrollListener scrollListener;

    private ItineraryDayAdapter mAdapter;


    @BindView(R.id.rv_itinerary_day_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_itinerary_day_error)
    TextView mError;

    @BindView(R.id.pb_itinerary_day_loading)
    ProgressBar mProgressBar;

    @BindView(R.id.btn_itinerary_day_try_again)
    Button mButtonTryAgain;

    GridLayoutManager layoutManager;


    @Inject
    ItineraryDayMvpPresenter<ItineraryDayMvpView> mPresenter;


    public static ItineraryDayFragment getInstance(Day day) {
        Bundle args = new Bundle();
        ItineraryDayFragment itineraryDayFragment = new ItineraryDayFragment();
        itineraryDayFragment.setArguments(args);
        args.putParcelable(PARAM_DAY, day);
        return itineraryDayFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            day = bundle.getParcelable(PARAM_DAY);
        }else{
            Log.e(TAG,"Error on create");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_itinerary_day,container,false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        layoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItineraryDayAdapter(this);

        mAdapter.setAttractions(day.getAttractions());

        mRecyclerView.setAdapter(mAdapter);

        mButtonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo add treatment
                Log.d(TAG,"Onclick ");
            }
        });


        // mRecyclerView.addOnScrollListener(scrollListener);

        return view;

    }

    @Override
    public void onClick(String attraction) {

    }

    public void showMap() {
        openDayRouteFragment(day.getAttractions());
    }

    private void openDayRouteFragment(List<String> attractions) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, DayRouteFragment.getInstance((ArrayList<String>) attractions), DayRouteFragment.TAG)
                .commit();
    }


}
