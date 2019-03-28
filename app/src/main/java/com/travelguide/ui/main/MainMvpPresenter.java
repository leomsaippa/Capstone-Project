package com.travelguide.ui.main;

import com.travelguide.ui.base.MvpPresenter;

public interface MainMvpPresenter <V extends MainMvpView> extends MvpPresenter<V> {
    void onConfirmItinerary();
}
