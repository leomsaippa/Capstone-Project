package com.travelguide.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import com.travelguide.di.ActivityContext;
import com.travelguide.di.PerActivity;
import com.travelguide.ui.fragments.attractionList.AttractionListMvpPresenter;
import com.travelguide.ui.login.LoginMvpPresenter;
import com.travelguide.ui.login.LoginMvpView;
import com.travelguide.ui.login.LoginPresenter;
import com.travelguide.ui.main.MainMvpPresenter;
import com.travelguide.ui.main.MainMvpView;
import com.travelguide.ui.main.MainPresenter;
import com.travelguide.ui.fragments.attractionDetail.AttractionDetailMvpPresenter;
import com.travelguide.ui.fragments.attractionDetail.AttractionDetailMvpView;
import com.travelguide.ui.fragments.attractionDetail.AttractionDetailPresenter;
import com.travelguide.ui.fragments.attractionList.AttractionListMvpView;
import com.travelguide.ui.fragments.attractionList.AttractionListPresenter;
import com.travelguide.ui.fragments.dayRoute.DayRouteMvpPresenter;
import com.travelguide.ui.fragments.dayRoute.DayRouteMvpView;
import com.travelguide.ui.fragments.dayRoute.DayRoutePresenter;
import com.travelguide.ui.fragments.itineraryDay.ItineraryDayMvpPresenter;
import com.travelguide.ui.fragments.itineraryDay.ItineraryDayMvpView;
import com.travelguide.ui.fragments.itineraryDay.ItineraryDayPresenter;
import com.travelguide.ui.fragments.itineraryDetail.ItineraryDetailMvpPresenter;
import com.travelguide.ui.fragments.itineraryDetail.ItineraryDetailMvpView;
import com.travelguide.ui.fragments.itineraryDetail.ItineraryDetailPresenter;
import com.travelguide.ui.fragments.itineraryList.ItineraryListMvpPresenter;
import com.travelguide.ui.fragments.itineraryList.ItineraryListMvpView;
import com.travelguide.ui.fragments.itineraryList.ItineraryListPresenter;
import com.travelguide.ui.fragments.searchPlace.SearchPlaceMvpPresenter;
import com.travelguide.ui.fragments.searchPlace.SearchPlaceMvpView;
import com.travelguide.ui.fragments.searchPlace.SearchPlacePresenter;
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
    AttractionListMvpPresenter<AttractionListMvpView> providesAttractionListPresenter(
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
