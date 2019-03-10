package com.travelguide.di.component;

import dagger.Component;
import com.travelguide.di.PerActivity;
import com.travelguide.di.module.ActivityModule;
import com.travelguide.ui.login.LoginActivity;
import com.travelguide.ui.main.MainActivity;
import com.travelguide.ui.main.attractionDetail.AttractionDetailFragment;
import com.travelguide.ui.main.attractionList.AttractionListFragment;
import com.travelguide.ui.main.dayRoute.DayRouteFragment;
import com.travelguide.ui.main.itineraryDay.ItineraryDayFragment;
import com.travelguide.ui.main.itineraryDetail.ItineraryDetailFragment;
import com.travelguide.ui.main.itineraryList.ItineraryListFragment;
import com.travelguide.ui.main.searchPlace.SearchPlaceFragment;

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
