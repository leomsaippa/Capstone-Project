package com.travelguide.ui.fragments.attractionDetail;

import com.travelguide.ui.base.MvpPresenter;

import org.joda.time.LocalDate;

import java.text.DateFormat;

public interface AttractionDetailMvpPresenter<V extends AttractionDetailMvpView> extends MvpPresenter<V> {
    void addAttraction(String name, LocalDate date);
}
