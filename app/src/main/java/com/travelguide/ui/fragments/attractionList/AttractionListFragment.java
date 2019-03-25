package com.travelguide.ui.fragments.attractionList;

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
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.attractionDetail.AttractionDetailFragment;
import com.travelguide.utils.EndlessRecyclerViewScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttractionListFragment extends BaseFragment implements AttractionListMvpView, AttractionsAdapter.AttractionsAdapterOnClickHandler{

    public static final String TAG = AttractionListFragment.class.getSimpleName();

    private int currentPage;

    private EndlessRecyclerViewScrollListener scrollListener;

    private AttractionsAdapter mAdapter;

    @BindView(R.id.rv_attractions_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_error)
    TextView mError;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @BindView(R.id.btn_try_again)
    Button mButtonTryAgain;

    GridLayoutManager layoutManager;


    @Inject
    AttractionListMvpPresenter<AttractionListMvpView> mPresenter;

    public static AttractionListFragment getInstance() {
        Bundle args = new Bundle();
        AttractionListFragment attractionListFragment = new AttractionListFragment();
        attractionListFragment.setArguments(args);
        return attractionListFragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_attraction_list,container,false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);


        layoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new AttractionsAdapter(this);
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

    private void loadAttractions(int currentPage) {
        //TODO adds logic to get more response from api
        mPresenter.onBtnLoadAttractionsClick();
    }

    @Override
    public void onClick(SearchPlaceResponse searchPlaceResponse) {
        openAttractionDetailFragment();
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
    public void openAttractionDetailFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, AttractionListFragment.getInstance(), AttractionDetailFragment.TAG)
                .commit();
    }
}
