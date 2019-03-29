package com.travelguide.ui.fragments.searchPlace;

import com.travelguide.ui.base.MvpPresenter;

import java.util.Date;

public interface SearchPlaceMvpPresenter<V extends SearchPlaceMvpView> extends MvpPresenter<V> {


    void onBtnSearchClick(String place, Date dateBeginTravel, Date dateEndTravel);

    void setApiEndPoint();
}
