package com.travelguide.ui.fragments.attractionDetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.travelguide.R;
import com.travelguide.data.network.model.Attraction;
import com.travelguide.data.network.model.Geometry;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.PlaceResult;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.searchPlace.calendar.DatePickerFragment;
import com.travelguide.ui.main.MainActivity;
import com.travelguide.utils.AppConstants;

import net.danlew.android.joda.JodaTimeAndroid;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttractionDetailFragment extends BaseFragment implements AttractionDetailMvpView, DatePickerFragment.OnSelectedDate{

    public static final String TAG = AttractionDetailFragment.class.getSimpleName();

    private static final String PARAM_SEARCH_RESULT = "SEARCH_RESULT";
    private static final String PARAM_ITINERARY = "PARAM_ITINERARY";


    PlaceResult searchPlaceResult;

    Itinerary itinerary;

    @BindView(R.id.tv_ratingAttraction)
    TextView mRating;

    @BindView(R.id.tv_nameAttraction)
    TextView mAttraction;

    @BindView(R.id.tv_openedNow)
    TextView mOpenedNow;

    @BindView(R.id.tv_total_users)
    TextView mTotalUser;

    @BindView(R.id.iv_attraction_detail)
    ImageView mIvAttractionDetail;

    @Inject
    AttractionDetailMvpPresenter<AttractionDetailMvpView> mPresenter;

    public static AttractionDetailFragment getInstance(PlaceResult searchPlaceResult, Itinerary itinerary) {
        Bundle args = new Bundle();
        args.putParcelable(PARAM_SEARCH_RESULT, searchPlaceResult);
        args.putParcelable(PARAM_ITINERARY, itinerary);
        AttractionDetailFragment attractionDetailFragment = new AttractionDetailFragment();
        attractionDetailFragment.setArguments(args);
        return attractionDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            searchPlaceResult = bundle.getParcelable(PARAM_SEARCH_RESULT);
            itinerary = bundle.getParcelable(PARAM_ITINERARY);
        }else{
            Log.e(TAG,"Error on create");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_attraction_detail,container,false);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);


        ((MainActivity) getActivity()).showFAB(AttractionDetailFragment.TAG);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String photoReference = searchPlaceResult.photos.get(0).getPhoto_reference();

            if (photoReference != null) {


                Log.d(TAG, "Photo reference " + photoReference);


                String url = AppConstants.API_ENDPOINT + mPresenter.generatePhotoQuery(photoReference);
                DefaultHttpClient hc = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(url);
                HttpContext context = new BasicHttpContext();
                hc.setRedirectHandler(new DefaultRedirectHandler() {
                    @Override
                    public URI getLocationURI(HttpResponse response,
                                              HttpContext context) throws org.apache.http.ProtocolException {

                        //Capture the Location header here - This is your redirected URL
                        System.out.println(Arrays.toString(response.getHeaders("Location")));

                        return super.getLocationURI(response, context);

                    }
                });

                // Response contains the image you want. If you test the redirect URL in a browser or REST CLIENT you can see it's data
                HttpResponse response = null;
                try {
                    response = hc.execute(httpget, context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.getStatusLine().getStatusCode() == 200) {
                    // Todo: use the Image response
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        InputStream instream = null;
                        try {
                            instream = entity.getContent();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bitmap bmp = BitmapFactory.decodeStream(instream);

                        mIvAttractionDetail.setImageBitmap(bmp);

                        try {
                            instream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println(response.getStatusLine().getStatusCode() + "");
                }
            }
        }else{
            mIvAttractionDetail.setBackground(getResources().getDrawable(R.drawable.noimage));
        }

        JodaTimeAndroid.init(getContext());


        mAttraction.setText(searchPlaceResult.name);
        String rating = "Rating " + String.valueOf(searchPlaceResult.rating);
        mRating.setText(rating);
        String totalUsers = "Total rating people: " + String.valueOf(searchPlaceResult.userRatingsTotal);
        mTotalUser.setText(totalUsers);
        boolean isOpenNow = false;
        if(searchPlaceResult.openingHours != null){
            isOpenNow = searchPlaceResult.openingHours.isOpen_now();
        }
        if(isOpenNow){
            mOpenedNow.setText(R.string.opened_now);
            mOpenedNow.setTextColor(getResources().getColor(R.color.quantum_googgreen));
        }else{
            mOpenedNow.setText(R.string.closed_now);
            mOpenedNow.setTextColor(getResources().getColor(R.color.quantum_googred));

        }
        return view;

    }


    public void showCalendar(){
        DatePickerFragment fragment = new DatePickerFragment();
        if(getFragmentManager()!=null)
            fragment.show(getChildFragmentManager(),DatePickerFragment.TAG);
    }

    @Override
    public void onSelectedDate(int year, int month, int dayOfMonth) {

        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        TimeZone timeZone = calendar.getTimeZone();
        DateTimeZone jodaDateTimeZone = DateTimeZone.forID(timeZone.getID());
        DateTime dateTime = new DateTime(calendar.getTimeInMillis(), jodaDateTimeZone);

        LocalDate localDate = dateTime.toLocalDate();

        addAttraction(searchPlaceResult.name, localDate,searchPlaceResult.getGeometry());

    }


    private void addAttraction(String name, LocalDate date, Geometry geometry){
        Attraction attraction = new Attraction();
        LocalDate dayBegin = itinerary.getDayBegin();
        int quantityDays = Days.daysBetween(dayBegin, date).getDays();

        if(quantityDays < 1 || quantityDays > itinerary.getNumber_days()){
            onSelectedDateError();
        }else {


            List<Attraction> attractions = itinerary.getList_days().get(quantityDays).getAttractions();
            if (attractions == null) {
                attractions = new ArrayList<>();
            }
            attraction.setLat(geometry.getLocation().getLat());
            attraction.setLng(geometry.getLocation().getLng());
            attraction.setName(name);
            attractions.add(attraction);
            itinerary.getList_days().get(quantityDays).setAttractions(attractions);
            Log.d(TAG, "Adding to itinerary " + itinerary.getList_days().get(quantityDays).getAttractions());
            mPresenter.updateItinerary(itinerary);

        }
    }

    private void onSelectedDateError() {
        Toast.makeText(getContext(), "This day is not present in your travel", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSelectedDateSuccess() {
        Toast.makeText(getContext(), searchPlaceResult.name + " added succesfully to day " , Toast.LENGTH_SHORT).show();

        if(getActivity()!=null){
            if(getActivity().getSupportFragmentManager()!=null){
                getActivity().onBackPressed();
            }
        }
    }
}
