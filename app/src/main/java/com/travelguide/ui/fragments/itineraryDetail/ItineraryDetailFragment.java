package com.travelguide.ui.fragments.itineraryDetail;

import android.os.Bundle;

import com.travelguide.R;
import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.attractionDetail.ItineraryDetailAdapter;
import com.travelguide.utils.EndlessRecyclerViewScrollListener;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItineraryDetailFragment extends BaseFragment implements ItineraryDetailMvpView, ItineraryDetailAdapter.ItineraryDetailAdapterOnClickHandler{

    public static final String TAG = ItineraryDetailFragment.class.getSimpleName();
    private static final String PARAM_ITINERARY = "PARAM_ITINERARY";

    private Itinerary itinerary;

    private EndlessRecyclerViewScrollListener scrollListener;

    private ItineraryDetailAdapter mAdapter;

    @BindView(R.id.rv_itinerary_detail_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_itinerary_detail_error)
    TextView mError;

    @BindView(R.id.pb_itinerary_detail_loading)
    ProgressBar mProgressBar;

    @BindView(R.id.btn_itinerary_detail_try_again)
    Button mButtonTryAgain;

    GridLayoutManager layoutManager;


    @Inject
    ItineraryDetailMvpPresenter<ItineraryDetailMvpView> mPresenter;

    public static ItineraryDetailFragment getInstance(Itinerary itinerary) {
        Bundle args = new Bundle();
        ItineraryDetailFragment itineraryDetailFragment = new ItineraryDetailFragment();
        itineraryDetailFragment.setArguments(args);
        args.putParcelable(PARAM_ITINERARY, itinerary);
        return itineraryDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            itinerary = bundle.getParcelable(PARAM_ITINERARY);
        }else{
            Log.e(TAG,"Error on create");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_itinerary_detail,container,false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        layoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItineraryDetailAdapter(this);

        mAdapter.setDays(itinerary.getList_days());

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
    public void onClick(Day day) {
    }
}
