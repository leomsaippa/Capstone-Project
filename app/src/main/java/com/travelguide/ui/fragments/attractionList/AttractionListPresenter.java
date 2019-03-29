package com.travelguide.ui.fragments.attractionList;

import com.travelguide.data.DataManager;
import com.travelguide.data.network.model.PlaceResult;
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

    @Override
    public void onBtnLoadAttractionsClick() {
        //todo adicionar logica para pegar mais lugares
    }

    @Override
    public void setCurrentPlace(String place) {
        getDataManager().setCurrentPlace(place);
    }
}
