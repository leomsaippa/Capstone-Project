package com.travelguide.ui.fragments.attractionList;

import com.travelguide.ui.base.MvpPresenter;

public interface AttractionListMvpPresenter<V extends AttractionListMvpView> extends MvpPresenter<V> {
    void onBtnLoadAttractionsClick();
}
