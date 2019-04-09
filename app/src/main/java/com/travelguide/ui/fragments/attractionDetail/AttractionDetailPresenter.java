package com.travelguide.ui.fragments.attractionDetail;

import com.travelguide.data.DataManager;
import com.travelguide.data.network.model.Itinerary;
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


    @Override
    public void updateItinerary(Itinerary itinerary) {
    //Todo por um ok na tela
        getDataManager().updateItinerary(itinerary);
    }
}
