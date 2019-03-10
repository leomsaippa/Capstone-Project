package com.travelguide.ui.main.itineraryDay;

import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ItineraryDayPresenter <V extends ItineraryDayMvpView> extends BasePresenter<V>
        implements ItineraryDayMvpPresenter<V>  {

    @Inject
    public ItineraryDayPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
