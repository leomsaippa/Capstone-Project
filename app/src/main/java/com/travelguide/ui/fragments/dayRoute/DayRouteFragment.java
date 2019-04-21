package com.travelguide.ui.fragments.dayRoute;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.travelguide.R;
import com.travelguide.data.network.model.Attraction;
import com.travelguide.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class DayRouteFragment extends BaseFragment implements DayRouteMvpView {

    public static final String TAG = DayRouteFragment.class.getSimpleName();
    public static final String PARAM_ATTRACTIONS = "PARAM_ATTRACTIONS";

    private List<Attraction> attractions;

    MapView mMapView;
    private GoogleMap googleMap;

    @Inject
    DayRouteMvpPresenter<DayRouteMvpView> mPresenter;

    public static DayRouteFragment getInstance(ArrayList<Attraction> attractions) {
        Bundle args = new Bundle();
        DayRouteFragment dayRouteFragment = new DayRouteFragment();
        dayRouteFragment.setArguments(args);
        args.putParcelableArrayList(PARAM_ATTRACTIONS, attractions);
        return dayRouteFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            attractions = bundle.getParcelableArrayList(PARAM_ATTRACTIONS);
        } else {
            Log.e(TAG, "Error on create");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_day_route, container, false);


        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(Objects.requireNonNull(getActivity()).getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(mMap -> {
            Log.d(TAG,"onMapReady");
            googleMap = mMap;


            drawRoute(attractions);
            // For showing a move to my location button
            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG,"Missing permissions");
            }else{
                googleMap.setMyLocationEnabled(true);

            }

        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    private void drawRoute(List<Attraction> attractions) {

        double firstLat = attractions.get(0).getLat();
        double firstLng = attractions.get(0).getLng();
        LatLng firstLatLng = new LatLng(firstLat, firstLng);
        googleMap.addMarker(new MarkerOptions().position(firstLatLng).title(attractions.get(0).getName()));

        double lastLat=0;
        double lastLng=0;
        LatLng lastLatLng = null;

        if (attractions.size() > 1) {
            for (int i = 0; i < attractions.size(); i++) {
                lastLat = attractions.get(i).getLat();
                lastLng = attractions.get(i).getLng();
                lastLatLng = new LatLng(lastLat, lastLng);
                googleMap.addMarker(new MarkerOptions().position(lastLatLng).title(attractions.get(i).getName()));

            }
        }

        //Define list to get all latlng for the route
        @SuppressWarnings("unchecked") List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(getString(R.string.maps_apikey))
                .build();
        if (lastLatLng != null) {
            //Removes ( and ) chars
            String firstLatLngString = String.valueOf(firstLat) +"," +String.valueOf(firstLng);
            String lastLatLngString = String.valueOf(lastLat) +"," +String.valueOf(lastLng);

            Log.d(TAG,"firstLatLngString lat " + firstLatLngString);
            Log.d(TAG,"lastLatLngString lat " + lastLatLngString);

            DirectionsApiRequest req = DirectionsApi.getDirections(context, firstLatLngString, lastLatLngString);
            try {
                DirectionsResult res = req.await();

                //Loop through legs and steps to get encoded polylines of each step
                if (res.routes != null && res.routes.length > 0) {
                    DirectionsRoute route = res.routes[0];

                    if (route.legs != null) {
                        for (int i = 0; i < route.legs.length; i++) {
                            DirectionsLeg leg = route.legs[i];
                            if (leg.steps != null) {
                                for (int j = 0; j < leg.steps.length; j++) {
                                    DirectionsStep step = leg.steps[j];
                                    if (step.steps != null && step.steps.length > 0) {
                                        for (int k = 0; k < step.steps.length; k++) {
                                            DirectionsStep step1 = step.steps[k];
                                            EncodedPolyline points1 = step1.polyline;
                                            if (points1 != null) {
                                                //Decode polyline and add points to list of route coordinates
                                                List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                for (com.google.maps.model.LatLng coord1 : coords1) {
                                                    path.add(new LatLng(coord1.lat, coord1.lng));
                                                }
                                            }
                                        }
                                    } else {
                                        EncodedPolyline points = step.polyline;
                                        if (points != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords = points.decodePath();
                                            for (com.google.maps.model.LatLng coord : coords) {
                                                path.add(new LatLng(coord.lat, coord.lng));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.getLocalizedMessage());
            }

            //Draw the polyline
            if (path.size() > 0) {
                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                googleMap.addPolyline(opts);
            }

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 15.0f));
        }
    }
}
