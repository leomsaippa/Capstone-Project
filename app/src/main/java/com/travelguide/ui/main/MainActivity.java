package com.travelguide.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.travelguide.R;
import com.travelguide.ui.base.BaseActivity;
import com.travelguide.ui.fragments.attractionDetail.AttractionDetailFragment;
import com.travelguide.ui.fragments.attractionList.AttractionListFragment;
import com.travelguide.ui.fragments.itineraryDay.ItineraryDayFragment;
import com.travelguide.ui.fragments.itineraryDetail.ItineraryDetailFragment;
import com.travelguide.ui.fragments.itineraryList.ItineraryListFragment;
import com.travelguide.ui.fragments.searchPlace.SearchPlaceFragment;

import static com.travelguide.utils.AppConstants.PARAM_EMAIL;
import static com.travelguide.utils.AppConstants.PARAM_NAME;


public class MainActivity extends BaseActivity implements MainMvpView, NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    String name;

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent()!=null) {
            name = getIntent().getStringExtra(PARAM_NAME);
            email = getIntent().getStringExtra(PARAM_EMAIL);
        }

        setSupportActionBar(mToolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView navName = hView.findViewById(R.id.tv_nv_header_title);
        TextView navEmail = hView.findViewById(R.id.tv_nv_header_subtitle);
        navName.setText(name);
        navEmail.setText(email);


        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        mPresenter.onAttach(this);

        mFab.hide();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        showSearchPlaceFragment();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
                getFragmentManager().beginTransaction().commit();

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_router) {
            mPresenter.onConfirmItinerary();
        } else if (id == R.id.nav_widget) {
            ItineraryDetailFragment fragment = (ItineraryDetailFragment) getSupportFragmentManager().findFragmentByTag(ItineraryDetailFragment.TAG);
            if (fragment != null) {
                fragment.addWidget();
            }else{
                Toast.makeText(this, "You can't add widget. Go to your list day.", Toast.LENGTH_SHORT).show();
            }

        }else if(id == R.id.nav_search){
            showSearchPlaceFragment();
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showSearchPlaceFragment () {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, SearchPlaceFragment.getInstance(), SearchPlaceFragment.TAG)
                .addToBackStack(SearchPlaceFragment.TAG)
                .commit();
    }

    @Override
    public void showItineraryListFragment () {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, ItineraryListFragment.getInstance(), ItineraryListFragment.TAG)
                .addToBackStack(ItineraryListFragment.TAG)
                .commit();
    }

    public void showFAB (String fragment) {
        if(fragment.equals(AttractionListFragment.TAG)){
            mFab.setImageDrawable(getResources().getDrawable(android.R.drawable.checkbox_on_background));
        }else if(fragment.equals(AttractionDetailFragment.TAG)) {
            mFab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_input_add
            ));
        }else if(fragment.equals(ItineraryDayFragment.TAG)){
            mFab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_dialog_map));
        }
        mFab.show();
    }

    public void showWidget(){
        Menu nav_Menu = mNavigationView.getMenu();
        nav_Menu.findItem(R.id.nav_widget).setVisible(true);
    }
    public void hideWidget(){
        Menu nav_Menu = mNavigationView.getMenu();
        nav_Menu.findItem(R.id.nav_widget).setVisible(false);
    }

    @OnClick(R.id.fab)
    public void onFabClick (View view){
        AttractionDetailFragment attractionDetailFragment = (AttractionDetailFragment) getSupportFragmentManager().findFragmentByTag(AttractionDetailFragment.TAG);
        if (attractionDetailFragment != null) {
            Toast.makeText(this, "Select the date to this attraction to your itinerary! ", Toast.LENGTH_SHORT).show();
            attractionDetailFragment.showCalendar();
        } else {
            ItineraryDayFragment itineraryDayFragment = (ItineraryDayFragment) getSupportFragmentManager().findFragmentByTag(ItineraryDayFragment.TAG);
            if (itineraryDayFragment != null) {
                itineraryDayFragment.showMap();
                mFab.hide();
            } else {
                AttractionListFragment attractionListFragment = (AttractionListFragment) getSupportFragmentManager().findFragmentByTag(AttractionListFragment.TAG);
                if (attractionListFragment != null) {
                    Toast.makeText(this, "Router saved!", Toast.LENGTH_SHORT).show();
                    mFab.hide();
                    showItineraryListFragment();

                }
            }

        }
    }

}
