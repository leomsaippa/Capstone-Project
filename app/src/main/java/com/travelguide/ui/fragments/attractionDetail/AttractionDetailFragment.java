package com.travelguide.ui.fragments.attractionDetail;

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

public class AttractionDetailFragment extends BaseFragment implements AttractionDetailMvpView{

    public static final String TAG = AttractionDetailFragment.class.getSimpleName();


    @Inject
    AttractionDetailMvpPresenter<AttractionDetailMvpView> mPresenter;
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
