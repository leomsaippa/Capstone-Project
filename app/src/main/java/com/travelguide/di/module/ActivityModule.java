package com.travelguide.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import com.travelguide.di.ActivityContext;
import com.travelguide.di.PerActivity;
import com.travelguide.ui.login.LoginMvpPresenter;
import com.travelguide.ui.login.LoginMvpView;
import com.travelguide.ui.login.LoginPresenter;
import com.travelguide.ui.main.MainMvpPresenter;
import com.travelguide.ui.main.MainMvpView;
import com.travelguide.ui.main.MainPresenter;
import com.travelguide.ui.main.attractionDetail.AttractionDetailMvpPresenter;
import com.travelguide.ui.main.attractionDetail.AttractionDetailMvpView;
import com.travelguide.ui.main.attractionDetail.AttractionDetailPresenter;
import com.travelguide.ui.main.attractionList.AttractionListMvpView;
import com.travelguide.ui.main.attractionList.AttractionListPresenter;
import com.travelguide.ui.main.dayRoute.DayRouteMvpPresenter;
import com.travelguide.ui.main.dayRoute.DayRouteMvpView;
import com.travelguide.ui.main.dayRoute.DayRoutePresenter;
import com.travelguide.ui.main.itineraryDay.ItineraryDayMvpPresenter;
import com.travelguide.ui.main.itineraryDay.ItineraryDayMvpView;
import com.travelguide.ui.main.itineraryDay.ItineraryDayPresenter;
import com.travelguide.ui.main.itineraryDetail.ItineraryDetailMvpPresenter;
import com.travelguide.ui.main.itineraryDetail.ItineraryDetailMvpView;
import com.travelguide.ui.main.itineraryDetail.ItineraryDetailPresenter;
import com.travelguide.ui.main.itineraryList.ItineraryListMvpPresenter;
import com.travelguide.ui.main.itineraryList.ItineraryListMvpView;
import com.travelguide.ui.main.itineraryList.ItineraryListPresenter;
import com.travelguide.ui.main.searchPlace.SearchPlaceMvpPresenter;
import com.travelguide.ui.main.searchPlace.SearchPlaceMvpView;
import com.travelguide.ui.main.searchPlace.SearchPlacePresenter;
import com.travelguide.utils.rx.AppSchedulerProvider;
import com.travelguide.utils.rx.SchedulerProvider;

@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity){
        this.mActivity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> providesMainPresenter(
            MainPresenter<MainMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> providesLoginPresenter(
            LoginPresenter<LoginMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    AttractionDetailMvpPresenter<AttractionDetailMvpView> providesAttractionDetailPresenter(
            AttractionDetailPresenter<AttractionDetailMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    AttractionListPresenter<AttractionListMvpView> providesAttractionListPresenter(
            AttractionListPresenter<AttractionListMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    DayRouteMvpPresenter<DayRouteMvpView> providesDayRoutePresenter(
            DayRoutePresenter<DayRouteMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    ItineraryDayMvpPresenter<ItineraryDayMvpView> providesItineraryDayPresenter(
            ItineraryDayPresenter<ItineraryDayMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    ItineraryDetailMvpPresenter<ItineraryDetailMvpView> providesItineraryDetailPresenter(
            ItineraryDetailPresenter<ItineraryDetailMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    ItineraryListMvpPresenter<ItineraryListMvpView> providesItineraryListPresenter(
            ItineraryListPresenter<ItineraryListMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    SearchPlaceMvpPresenter<SearchPlaceMvpView> providesSearchPlacePresenter(
            SearchPlacePresenter<SearchPlaceMvpView> presenter){
        return presenter;
    }


}
