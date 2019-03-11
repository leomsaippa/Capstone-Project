package com.travelguide.ui.main.searchPlace;

import com.travelguide.ui.base.MvpPresenter;

public interface SearchPlaceMvpPresenter<V extends SearchPlaceMvpView> extends MvpPresenter<V> {


    void onBtnSearchClick();
}
