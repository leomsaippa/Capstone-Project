package com.travelguide.ui.fragments.dayRoute;

import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DayRoutePresenter <V extends DayRouteMvpView> extends BasePresenter<V>
        implements DayRouteMvpPresenter<V> {

    @Inject
    public DayRoutePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
