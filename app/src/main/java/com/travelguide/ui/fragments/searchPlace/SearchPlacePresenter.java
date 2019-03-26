package com.travelguide.ui.fragments.searchPlace;

import android.util.Log;

import com.travelguide.data.DataManager;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.ui.base.BasePresenter;
import com.travelguide.utils.AppConstants;
import com.travelguide.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SearchPlacePresenter <V extends SearchPlaceMvpView> extends BasePresenter<V>
        implements SearchPlaceMvpPresenter<V>  {

    public static final String TAG = SearchPlacePresenter.class.getSimpleName();
    @Inject
    public SearchPlacePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onBtnSearchClick(final String place) {

        String query = getDataManager().generateQuery(place);

        if(query != null){
      //      getMvpView().showLoading(null,getDataManager().getMessageLoading());

            getCompositeDisposable().add(getDataManager().apiGetPlaces(
                    query)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<SearchPlaceResponse>() {
                        @Override
                        public void accept(SearchPlaceResponse placeResult) throws Exception {
                        /*    if (!isViewAttached()) {
                                return;
                            }*/

                            //getMvpView().hideLoading();
                            Log.d(TAG, "Place result " + placeResult.getPlaceResult().get(0).formattedAddress);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                          /*  if (!isViewAttached()) {
                                return;
                            }
                          */
                          Log.d(TAG,"Error " + throwable.getMessage());
                            //getMvpView().hideLoading();
                        }
                    })
            );
        }else{
            Log.e(TAG,"Place request null");
        }

    }

    @Override
    public void setApiEndPoint() {
        getDataManager().apiSetEndPoint(AppConstants.API_ENDPOINT);
    }
}
