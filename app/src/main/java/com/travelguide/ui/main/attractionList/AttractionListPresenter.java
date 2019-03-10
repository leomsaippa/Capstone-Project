package com.travelguide.ui.main.attractionList;

import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AttractionListPresenter<V extends AttractionListMvpView> extends BasePresenter<V>
        implements AttractionListMvpPresenter<V>  {

    @Inject
    public AttractionListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
