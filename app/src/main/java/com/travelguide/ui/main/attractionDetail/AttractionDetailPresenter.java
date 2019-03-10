package com.travelguide.ui.main.attractionDetail;

import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AttractionDetailPresenter<V extends AttractionDetailMvpView> extends BasePresenter<V>
        implements AttractionDetailMvpPresenter<V>  {

    @Inject
    public AttractionDetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
