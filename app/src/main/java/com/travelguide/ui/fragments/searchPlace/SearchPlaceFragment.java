package com.travelguide.ui.fragments.searchPlace;

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

    public static final String TAG = SearchPlaceFragment.class.getSimpleName();

    @BindView(R.id.btn_search)
    Button mBtnSearch;

    @Inject
    SearchPlaceMvpPresenter<SearchPlaceMvpView> mPresenter;


    public static SearchPlaceFragment newInstance() {
        Bundle args = new Bundle();
        SearchPlaceFragment searchPlaceFragment = new SearchPlaceFragment();
        searchPlaceFragment.setArguments(args);
        return searchPlaceFragment;
    }


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
