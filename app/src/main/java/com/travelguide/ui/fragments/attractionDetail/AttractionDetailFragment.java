package com.travelguide.ui.fragments.attractionDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelguide.R;
import com.travelguide.data.network.model.PlaceResult;
import com.travelguide.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class AttractionDetailFragment extends BaseFragment implements AttractionDetailMvpView{

    public static final String TAG = AttractionDetailFragment.class.getSimpleName();

    private static final String PARAM_SEARCH_RESULT = "SEARCH_RESULT";

    @Inject
    AttractionDetailMvpPresenter<AttractionDetailMvpView> mPresenter;

    public static AttractionDetailFragment getInstance(PlaceResult searchPlaceResult) {
        Bundle args = new Bundle();
        args.putParcelable(PARAM_SEARCH_RESULT, searchPlaceResult);
        AttractionDetailFragment attractionDetailFragment = new AttractionDetailFragment();
        attractionDetailFragment.setArguments(args);
        return attractionDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_attraction_detail,container,false);


        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        return view;

    }

}
