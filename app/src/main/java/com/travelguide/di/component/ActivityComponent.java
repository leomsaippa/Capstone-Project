package com.travelguide.di.component;

import dagger.Component;
import com.travelguide.di.PerActivity;
import com.travelguide.di.module.ActivityModule;
import com.travelguide.ui.login.LoginActivity;
import com.travelguide.ui.main.MainActivity;
import com.travelguide.ui.fragments.attractionDetail.AttractionDetailFragment;
import com.travelguide.ui.fragments.attractionList.AttractionListFragment;
import com.travelguide.ui.fragments.dayRoute.DayRouteFragment;
import com.travelguide.ui.fragments.itineraryDay.ItineraryDayFragment;
import com.travelguide.ui.fragments.itineraryDetail.ItineraryDetailFragment;
import com.travelguide.ui.fragments.itineraryList.ItineraryListFragment;
import com.travelguide.ui.fragments.searchPlace.SearchPlaceFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject (MainActivity mainActivity);

    void inject (LoginActivity loginActivity);

    void inject (AttractionDetailFragment attractionDetailFragment);

    void inject (AttractionListFragment attractionListFragment);

    void inject (DayRouteFragment dayRouteFragment);

    void inject (ItineraryDayFragment itineraryDayFragment);

    void inject (ItineraryDetailFragment itineraryDetailFragment);

    void inject (ItineraryListFragment itineraryListFragment);

    void inject (SearchPlaceFragment searchPlaceFragment);

}
