package com.travelguide.ui.fragments.searchPlace;

import android.util.Log;

import com.travelguide.data.DataManager;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.AppConstants;
import com.travelguide.utils.CommonUtils;
import com.travelguide.utils.rx.SchedulerProvider;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.travelguide.utils.AppConstants.ZERO_RESULTS;

public class SearchPlacePresenter <V extends SearchPlaceMvpView> extends BasePresenter<V>
        implements SearchPlaceMvpPresenter<V>  {

    Itinerary itinerary = null;

    public static final String TAG = SearchPlacePresenter.class.getSimpleName();
    @Inject
    public SearchPlacePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getFormatedAddress(String city) {
        String query = getDataManager().generateTextCityQuery(city);

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
                        getMvpView().hideLoading();
                        getMvpView().search(placeResponse.getPlaceResult().get(0).photos.get(0).photo_reference);
                    }, throwable -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        Log.d(TAG, "Error " + throwable.getMessage());
                        getMvpView().hideLoading();
                        getMvpView().showInternetError();
                    })
            );
        } else {
            Log.e(TAG, "Place request null");
        }
    }

    @Override
    public Itinerary onBtnSearchClick(final String place, LocalDate dateBeginTravel, LocalDate dateEndTravel, String photo_reference) {

        int quantityDays = Days.daysBetween(dateBeginTravel,dateEndTravel).getDays();

        if (dateBeginTravel.toString().isEmpty()){
            getMvpView().onErrorEmptyBeginTravel();
        }else {
            if (dateEndTravel.toString().isEmpty()) {
                getMvpView().onErrorEmptyEndTravel();
            } else {
                if (quantityDays<=0) {
                    getMvpView().onErrorInvalidDate();
                } else {

                    if (place == null || place.isEmpty()) {
                        getMvpView().onErrorEmptyPlace();
                    } else {

                        String query = getDataManager().generateTextPlaceQuery(place);

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
                                        if(placeResponse.getStatus().equals(ZERO_RESULTS)){
                                            getMvpView().hideLoading();
                                            getMvpView().showZeroResultError();
                                        }else {
                                            itinerary = getDataManager().createItinerary(place, quantityDays, dateBeginTravel,
                                                    dateEndTravel, CommonUtils.createDays(quantityDays), photo_reference);
                                            Log.d(TAG, "Place Response " + placeResponse.getPlaceResult().get(0).formattedAddress);
                                            getMvpView().hideLoading();
                                            getMvpView().openAttractionListFragment(placeResponse, itinerary);
                                        }
                                    }, throwable -> {
                                        if (!isViewAttached()) {
                                            return;
                                        }
                                        Log.d(TAG, "Error " + throwable.getMessage());
                                        getMvpView().hideLoading();
                                        getMvpView().showInternetError();
                                    })
                            );
                        } else {
                            Log.e(TAG, "Place request null");
                        }
                    }
                }
            }
        }
        return itinerary;
    }

    @Override
    public void setApiEndPoint() {
        getDataManager().apiSetEndPoint(AppConstants.API_ENDPOINT);
    }
}
