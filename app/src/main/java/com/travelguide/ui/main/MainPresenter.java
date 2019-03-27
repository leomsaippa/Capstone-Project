package com.travelguide.ui.main;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.rx.SchedulerProvider;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V>  {


    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onFabClick() {
        getDataManager().createItinerary(getDataManager().getPlace());
    }
}
