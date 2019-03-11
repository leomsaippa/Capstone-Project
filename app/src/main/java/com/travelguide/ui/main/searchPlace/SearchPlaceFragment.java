package com.travelguide.ui.main.searchPlace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.travelguide.R;
import com.travelguide.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPlaceFragment extends BaseFragment implements SearchPlaceMvpView {

    @BindView(R.id.btn_search)
    Button mBtnSearch;

    @Inject
    SearchPlaceMvpPresenter<SearchPlaceMvpView> mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_search_place,container,false);


        getActivityComponent().inject(this);

        ButterKnife.bind(this,view);

        mPresenter.onAttach(this);

        return view;

    }

    @OnClick(R.id.btn_search)
    public void onClickBtnSearch(View view){
        mPresenter.onBtnSearchClick();
    }
}
