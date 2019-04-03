package com.travelguide.ui.fragments.searchPlace;

import com.travelguide.ui.base.MvpPresenter;

import org.joda.time.LocalDate;

public interface SearchPlaceMvpPresenter<V extends SearchPlaceMvpView> extends MvpPresenter<V> {


    void onBtnSearchClick(String place, LocalDate dateBeginTravel, LocalDate dateEndTravel);

    void setApiEndPoint();
}
