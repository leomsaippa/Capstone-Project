package com.travelguide.ui.fragments.attractionDetail;

import com.travelguide.ui.base.MvpPresenter;

import java.util.Date;

public interface AttractionDetailMvpPresenter<V extends AttractionDetailMvpView> extends MvpPresenter<V> {
    void addAttraction(String name, Date date);
}
