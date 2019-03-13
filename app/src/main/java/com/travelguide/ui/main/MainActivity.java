package com.travelguide.ui.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.travelguide.R;
import com.travelguide.ui.base.BaseActivity;
import com.travelguide.ui.fragments.searchPlace.SearchPlaceFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(mToolbar);


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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //Não faz naada para não voltar a tela de Login
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showSearchPlaceFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, SearchPlaceFragment.getInstance(), SearchPlaceFragment.TAG)
                .commit();
    }

//
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
//        Log.d(TAG,"onDateSet" + calendar);
//        SearchPlaceFragment fragment =  (SearchPlaceFragment) getSupportFragmentManager().findFragmentByTag(SearchPlaceFragment.TAG);
//        if(fragment != null)
//            fragment.onCalendarResult(calendar);
//    }
}
