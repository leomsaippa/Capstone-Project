package com.travelguide.ui.fragments.itineraryList;

import android.util.Log;

import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ItineraryListPresenter<V extends ItineraryListMvpView> extends BasePresenter<V>
    implements ItineraryListMvpPresenter<V>{

    public static final String TAG = ItineraryListPresenter.class.getSimpleName();
    @Inject
    public ItineraryListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onBtnLoadItinerariesClick() {
        Log.d(TAG,"onBtnLoadItinerariesClick");
    }

    @Override
    public String generateCityQuery(String place) {
        return getDataManager().generateTextCityQuery(place);
    }

}
