package com.travelguide.ui.main.itineraryDetail;

import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ItineraryDetailPresenter<V extends ItineraryDetailMvpView> extends BasePresenter<V>
    implements ItineraryDetailMvpPresenter<V>{

    @Inject
    public ItineraryDetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
