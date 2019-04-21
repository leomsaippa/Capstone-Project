package com.travelguide.ui.fragments.attractionList;

import android.app.Fragment;
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
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.PlaceResult;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.ui.SimpleDividerItemDecoration;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.attractionDetail.AttractionDetailFragment;
import com.travelguide.ui.main.MainActivity;
import com.travelguide.utils.EndlessRecyclerViewScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  AttractionListFragment extends BaseFragment implements AttractionListMvpView, AttractionsAdapter.AttractionsAdapterOnClickHandler{

    public static final String TAG = AttractionListFragment.class.getSimpleName();
    private static final String PARAM_SEARCH_RESPONSE = "SEARCH_RESPONSE_LIST";
    private static final String PARAM_ITINERARY = "PARAM_ITINERARY";

    private int currentPage;

    private EndlessRecyclerViewScrollListener scrollListener;

    private AttractionsAdapter mAdapter;

    private Itinerary itinerary;

    @BindView(R.id.rv_attractions_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_error)
    TextView mError;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @BindView(R.id.btn_try_again)
    Button mButtonTryAgain;

    GridLayoutManager layoutManager;

    private SearchPlaceResponse mSearchPlaceResponseList;

    @Inject
    AttractionListMvpPresenter<AttractionListMvpView> mPresenter;

    public static AttractionListFragment getInstance(SearchPlaceResponse searchPlaceResponseList, Itinerary itinerary) {
        Bundle args = new Bundle();
        args.putParcelable(PARAM_SEARCH_RESPONSE, searchPlaceResponseList);
        args.putParcelable(PARAM_ITINERARY, itinerary);
        AttractionListFragment attractionListFragment = new AttractionListFragment();
        attractionListFragment.setArguments(args);
        return attractionListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mSearchPlaceResponseList = bundle.getParcelable(PARAM_SEARCH_RESPONSE);
            itinerary = bundle.getParcelable(PARAM_ITINERARY);
        } else {
            Log.e(TAG, "Error on create");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_attraction_list, container, false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        ((MainActivity)getActivity()).showFAB(AttractionListFragment.TAG);


        layoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        mAdapter = new AttractionsAdapter(this);
        setPlaceResponseList();
        setItinerary();
        mRecyclerView.setAdapter(mAdapter);

        mButtonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAttractions(currentPage);
            }
        });


        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "onLoad More " + page + "\n Total items: " + totalItemsCount);
                currentPage = page;
                loadAttractions(currentPage);
            }
        };

        mRecyclerView.addOnScrollListener(scrollListener);

        return view;

    }

    private void setItinerary() {
        mAdapter.setItinerary(itinerary);
        mAdapter.notifyDataSetChanged();
    }

    private void setPlaceResponseList() {
        mAdapter.setSearchPlaceResponseList(mSearchPlaceResponseList.getPlaceResult());
        mAdapter.notifyDataSetChanged();
    }

    private void loadAttractions(int currentPage) {
        //TODO adds logic to get more response from api
        mPresenter.onBtnLoadAttractionsClick();
    }

    @Override
    public void onClick(PlaceResult searchPlaceResult, Itinerary itinerary) {
        openAttractionDetailFragment(searchPlaceResult, itinerary);
    }

    private void showError(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mError.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mButtonTryAgain.setVisibility(View.VISIBLE);
    }

    private void showLoading(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mError.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mButtonTryAgain.setVisibility(View.INVISIBLE);
    }

    private void showList(){
        mRecyclerView.setVisibility(View.VISIBLE);
        mError.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mButtonTryAgain.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openAttractionDetailFragment(PlaceResult searchPlaceResult, Itinerary itinerary) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, AttractionDetailFragment.getInstance(searchPlaceResult, itinerary), AttractionDetailFragment.TAG)
                .addToBackStack(AttractionDetailFragment.TAG)
                .commit();
    }
}
