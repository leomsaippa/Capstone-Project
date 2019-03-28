package com.travelguide.ui.fragments.searchPlace;

import android.util.Log;

import com.travelguide.data.DataManager;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.AppConstants;
import com.travelguide.utils.rx.SchedulerProvider;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SearchPlacePresenter <V extends SearchPlaceMvpView> extends BasePresenter<V>
        implements SearchPlaceMvpPresenter<V>  {

    public static final String TAG = SearchPlacePresenter.class.getSimpleName();
    @Inject
    public SearchPlacePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onBtnSearchClick(final String place, Date dateBeginTravel, Date dateEndTravel) {
        if (dateBeginTravel == null || dateBeginTravel.toString().isEmpty()){
            getMvpView().onErrorEmptyBeginTravel();
        }else {
            if (dateEndTravel == null || dateEndTravel.toString().isEmpty()) {
                getMvpView().onErrorEmptyEndTravel();
            } else {
                if (dateEndTravel.before(dateBeginTravel)) {
                    getMvpView().onErrorInvalidDate();
                } else {
                    long quantityDays = dateEndTravel.getTime() - dateBeginTravel.getTime();
                    getDataManager().setQuantityDays(quantityDays);
                    getDataManager().setDateBeginTravel(dateBeginTravel);
                    getDataManager().setEndTravel(dateEndTravel);
                    if (place == null || place.isEmpty()) {
                        getMvpView().onErrorEmptyPlace();
                    } else {

                        String query = getDataManager().generateQuery(place);

                        if (query != null) {
                            getMvpView().showLoading(null, getDataManager().getMessageLoading());

                            getCompositeDisposable().add(getDataManager().apiGetPlaces(
                                    query)
                                    .subscribeOn(getSchedulerProvider().io())
                                    .observeOn(getSchedulerProvider().ui())
                                    .subscribe(placeResponse -> {
                                        if (!isViewAttached()) {
                                            return;
                                        }
                                        Log.d(TAG, "Place Response " + placeResponse.getPlaceResult().get(0).formattedAddress);
                                        getMvpView().hideLoading();
                                        getMvpView().openAttractionListFragment(placeResponse);
                                    }, throwable -> {
                                        if (!isViewAttached()) {
                                            return;
                                        }
                                        Log.d(TAG, "Error " + throwable.getMessage());
                                        getMvpView().hideLoading();
                                    })
                            );
                        } else {
                            Log.e(TAG, "Place request null");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setApiEndPoint() {
        getDataManager().apiSetEndPoint(AppConstants.API_ENDPOINT);
    }
}
